package org.joyroom.carespoon.ui.friends

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import org.joyroom.carespoon.R
import org.joyroom.carespoon.data.CareSpoonSharedPreferences
import org.joyroom.carespoon.data.local.FriendsData
import org.joyroom.carespoon.data.remote.response.friend.ResponseSeniorFriendList
import org.joyroom.carespoon.data.remote.response.friend.ResponseSeniorFriendListItem
import org.joyroom.carespoon.data.remote.response.friend.ResponseViewerFriendList
import org.joyroom.carespoon.data.remote.response.friend.ResponseViewerFriendListItem
import org.joyroom.carespoon.databinding.ActivityFriendsBinding
import org.joyroom.carespoon.ui.setting.SettingActivity
import org.joyroom.carespoon.ui.viewModel.FriendsViewModel

class FriendsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFriendsBinding
    private val viewModel: FriendsViewModel by viewModels()
    private var seniorFriendsAdapter = SeniorFriendsAdapter()
    private var viewerFriendsAdapter = ViewerFriendsAdapter()
    private lateinit var seniorFriendList: List<ResponseSeniorFriendListItem>
    private lateinit var viewerFriendList: List<ResponseViewerFriendListItem>
    private val myUUID = CareSpoonSharedPreferences.getUUID()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFriendsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUUID()
        setByRole()
        setKeyboard()
        setIntent()
    }

    private fun setUUID() {
        binding.tvUniqueCodeValue.text = CareSpoonSharedPreferences.getUUID()
    }

    private fun setByRole() {
        val myRole = CareSpoonSharedPreferences.getUserRole()
        if (myRole == "viewer") {
            setViewerAdapter()
            setViewerFriendObserver()
            if (myUUID != null) {
                viewModel.requestViewerFriendList(myUUID)
            }

        } else {
            setSeniorAdapter()
            setSeniorFriendObserver()
            if (myUUID != null) {
                viewModel.requestSeniorFriendList(myUUID)
            }
        }
    }

    private fun setSeniorFriendObserver() {
        viewModel.seniorFriendList.observe(this, Observer { friendList ->
            with(binding.rvFriendsList.adapter as SeniorFriendsAdapter) {
                setFriendList(friendList)
                seniorFriendList = friendList.sortedBy { it.name }
            }
        })
    }

    private fun setViewerFriendObserver() {
        viewModel.viewerFriendList.observe(this, Observer { friendList ->
            with(binding.rvFriendsList.adapter as ViewerFriendsAdapter) {
                setFriendList(friendList)
                viewerFriendList = friendList.sortedBy { it.name }
            }
        })
    }

    private fun setSeniorAdapter() {
        binding.rvFriendsList.adapter = seniorFriendsAdapter

        seniorFriendsAdapter.setOnItemClickListener { _, pos ->
            for (changePos in seniorFriendList.indices) {
                if (viewModel.seniorFriendList.value?.get(changePos)?.name == seniorFriendList[pos].name) myUUID?.let {
                    viewModel.requestDeleteFriend(
                        it, seniorFriendList[pos].uuid
                    )
                    Toast.makeText(this, R.string.delete, Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.etSearch.addTextChangedListener {
            seniorFriendsAdapter.setSearchWord(binding.etSearch.text.toString())
        }
    }

    private fun setViewerAdapter() {
        binding.rvFriendsList.adapter = viewerFriendsAdapter

        viewerFriendsAdapter.setOnItemClickListener { _, pos ->
            for (changePos in viewerFriendList.indices) {
                if (viewModel.viewerFriendList.value?.get(changePos)?.name == viewerFriendList[pos].name) myUUID?.let {
                    viewModel.requestDeleteFriend(
                        viewerFriendList[pos].uuid,
                        it
                    )
                    Toast.makeText(this, R.string.delete, Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.etSearch.addTextChangedListener {
            viewerFriendsAdapter.setSearchWord(binding.etSearch.text.toString())
        }
    }

    private fun setKeyboard(){
        binding.etSearch.setOnEditorActionListener { v, actionId, event ->
            var handled = false
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(binding.etSearch.windowToken, 0)
                handled = true
            }
            handled
        }
    }

    private fun setIntent() {
        binding.ivAdd.setOnClickListener {
            startActivity(Intent(this, AddFriendActivity::class.java))
        }
    }
}