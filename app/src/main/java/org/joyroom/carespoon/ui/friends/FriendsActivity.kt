package org.joyroom.carespoon.ui.friends

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.joyroom.carespoon.data.local.FriendsData
import org.joyroom.carespoon.databinding.ActivityFriendsBinding
import org.joyroom.carespoon.ui.setting.SettingActivity

class FriendsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFriendsBinding
    private lateinit var friendsAdapter: FriendsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFriendsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
        setIntent()
    }

    private fun initAdapter(){
        friendsAdapter = FriendsAdapter()
        binding.rvFriendsList.adapter = friendsAdapter

        friendsAdapter.friendsList.addAll(
            listOf(
                FriendsData("구미진"),
                FriendsData("김현아"),
                FriendsData("도소현"),
            )
        )

        friendsAdapter.notifyDataSetChanged()
    }

    private fun setIntent(){
        binding.ivAdd.setOnClickListener {
            startActivity(Intent(this, AddFriendActivity::class.java))
        }
    }
}