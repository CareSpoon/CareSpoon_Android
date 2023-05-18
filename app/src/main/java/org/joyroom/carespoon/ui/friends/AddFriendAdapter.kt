package org.joyroom.carespoon.ui.friends

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.joyroom.carespoon.data.local.FriendsData
import org.joyroom.carespoon.data.local.UserData
import org.joyroom.carespoon.databinding.ItemFriendListBinding
import org.joyroom.carespoon.databinding.ItemUserListBinding

class AddFriendAdapter : RecyclerView.Adapter<AddFriendAdapter.UserViewHolder>() {
    val userList = mutableListOf<UserData>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserViewHolder {
        val binding = ItemUserListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.onBind(userList[position])
    }

    override fun getItemCount(): Int = userList.size

    class UserViewHolder(private val binding: ItemUserListBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: UserData){
            binding.tvUserName.text = data.name
        }
    }

}

