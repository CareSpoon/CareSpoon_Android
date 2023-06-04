package org.whocare.carespoon.ui.friends

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.whocare.carespoon.data.remote.response.friend.ResponseSeniorFriendListItem
import org.whocare.carespoon.databinding.ItemFriendListBinding

class SeniorFriendsAdapter : RecyclerView.Adapter<SeniorFriendsAdapter.FriendsViewHolder>() {
    private var listener: ((ResponseSeniorFriendListItem, Int) -> Unit)? = null
    private var friendList = emptyList<ResponseSeniorFriendListItem>()
    private var searchWord: String = ""
    var mPosition = 0

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FriendsViewHolder {
        val binding = ItemFriendListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FriendsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FriendsViewHolder, position: Int) {
        holder.onBind(friendList[position])
    }

    override fun getItemCount(): Int = friendList.size

    inner class FriendsViewHolder(private val binding: ItemFriendListBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseSeniorFriendListItem){
            binding.tvFriendsName.text = data.name

            filteringForSearching(data)
            setClinkListenerOnPosition(data)
        }

        private fun filteringForSearching(data: ResponseSeniorFriendListItem){
            if (data.name.startsWith(searchWord)) {
                binding.clFriendItem.visibility = View.VISIBLE
                binding.clFriendItem.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
            } else {
                binding.clFriendItem.visibility = View.GONE
                binding.clFriendItem.layoutParams.height = 0
            }
        }

        private fun setClinkListenerOnPosition(data: ResponseSeniorFriendListItem){
            binding.btnDelete.setOnClickListener {
                val pos = adapterPosition
                mPosition = pos
                listener?.invoke(data, mPosition)
            }
        }
    }

    fun setOnItemClickListener(listener: ((ResponseSeniorFriendListItem, Int) -> Unit)?) {
        this.listener = listener
    }

    fun setSearchWord(text: String) {
        searchWord = text
        notifyDataSetChanged()
    }

    fun setFriendList(friendList: List<ResponseSeniorFriendListItem>) {
        this.friendList = friendList.sortedBy { it.name }
        notifyDataSetChanged()
    }



}

