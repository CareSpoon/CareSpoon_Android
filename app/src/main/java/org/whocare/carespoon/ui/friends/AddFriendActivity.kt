package org.whocare.carespoon.ui.friends

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import org.whocare.carespoon.R
import org.whocare.carespoon.data.CareSpoonSharedPreferences
import org.whocare.carespoon.databinding.ActivityAddFriendBinding
import org.whocare.carespoon.ui.viewModel.FriendsViewModel

class AddFriendActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddFriendBinding
    private val viewModel: FriendsViewModel by viewModels()
    private lateinit var friendUUID: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddFriendBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSearch()
        initClickListener()
    }

    private fun initSearch(){
        binding.etSearch.setOnEditorActionListener { v, actionId, event ->
            var handled = false
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val uuid = binding.etSearch.text
                if(uuid.contains(".")) {
                    Toast.makeText(this, R
                        .string.do_not_contain_dot, Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.searchUser(uuid)
                    setUserObserver()
                }

                // 키보드 내리기
                val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(binding.etSearch.windowToken, 0)
                handled = true
            }
            handled
        }
    }

    private fun setUserObserver(){
        viewModel.userList.observe(this, Observer { user ->
            if(user == null){
                binding.tvUserNull.visibility = View.VISIBLE
            }else{
                binding.tvUserName.visibility = View.VISIBLE
                binding.tvUserName.text = user.name
                binding.button.visibility = View.VISIBLE
                friendUUID = user.uuid
            }
        })
    }

    private fun initClickListener(){
        binding.button.setOnClickListener{
            addFriend()
            Toast.makeText(this, R.string.add, Toast.LENGTH_SHORT).show()
        }
    }

    private fun addFriend(){
        val myRole = CareSpoonSharedPreferences.getUserRole()
        val myUUID = CareSpoonSharedPreferences.getUUID()
        if(myRole == "viewer") myUUID?.let { viewModel.requestAddFriend(it, friendUUID) }
        else myUUID?.let { viewModel.requestAddFriend(friendUUID, it) }
    }
}