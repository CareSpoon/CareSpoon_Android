package org.joyroom.carespoon.ui.friends

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.joyroom.carespoon.data.local.FriendsData
import org.joyroom.carespoon.databinding.ItemFriendListBinding

class FriendsAdapter : RecyclerView.Adapter<FriendsAdapter.FriendsViewHolder>() {
    val friendsList = mutableListOf<FriendsData>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FriendsViewHolder {
        val binding = ItemFriendListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FriendsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FriendsViewHolder, position: Int) {
        holder.onBind(friendsList[position])
    }

    override fun getItemCount(): Int = friendsList.size

    class FriendsViewHolder(private val binding: ItemFriendListBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: FriendsData){
            binding.tvFriendsName.text = data.name
        }
    }

}

