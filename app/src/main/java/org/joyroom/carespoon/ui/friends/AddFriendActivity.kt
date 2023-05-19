package org.joyroom.carespoon.ui.friends

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import org.joyroom.carespoon.data.CareSpoonSharedPreferences
import org.joyroom.carespoon.data.local.FriendsData
import org.joyroom.carespoon.data.local.UserData
import org.joyroom.carespoon.databinding.ActivityAddFriendBinding
import org.joyroom.carespoon.databinding.ActivityFriendsBinding
import org.joyroom.carespoon.ui.viewModel.FriendsViewModel

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
            Log.d("***************Entered", " ")
            var handled = false
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val uuid = binding.etSearch.text
                viewModel.requestUser(uuid)
                setUserObserver()
                handled = true
            }
            handled
        }
    }
    private fun setUserObserver(){
        viewModel.userList.observe(this, Observer { user ->
            binding.tvUserName.visibility = View.VISIBLE
            binding.tvUserName.text = user.name
            binding.button.visibility = View.VISIBLE
            friendUUID = user.uuid
        })
    }

    private fun initClickListener(){
        binding.button.setOnClickListener{
            addFriend()
        }
    }

    private fun addFriend(){
        val myRole = CareSpoonSharedPreferences.getUserRole()
        val myUUID = CareSpoonSharedPreferences.getUUID()
        if(myRole == "viewer") myUUID?.let { viewModel.requestAddFriend(it, friendUUID) }
        else myUUID?.let { viewModel.requestAddFriend(friendUUID, it) }
    }

}