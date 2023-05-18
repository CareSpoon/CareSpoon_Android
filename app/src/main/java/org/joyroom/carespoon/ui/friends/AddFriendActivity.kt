package org.joyroom.carespoon.ui.friends

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.joyroom.carespoon.data.local.FriendsData
import org.joyroom.carespoon.data.local.UserData
import org.joyroom.carespoon.databinding.ActivityAddFriendBinding
import org.joyroom.carespoon.databinding.ActivityFriendsBinding

class AddFriendActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddFriendBinding
    private lateinit var addFriendAdapter: AddFriendAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddFriendBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
    }

    private fun initAdapter(){
        addFriendAdapter = AddFriendAdapter()
        binding.rvUserList.adapter = addFriendAdapter

        addFriendAdapter.userList.addAll(
            listOf(
                UserData("구미진", ""),
                UserData("김현아", ""),
                UserData("도소현", ""),
            )
        )

        addFriendAdapter.notifyDataSetChanged()
    }
}