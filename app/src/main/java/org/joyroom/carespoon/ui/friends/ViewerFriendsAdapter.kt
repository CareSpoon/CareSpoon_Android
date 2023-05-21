package org.joyroom.carespoon.ui.friends

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.joyroom.carespoon.data.remote.response.friend.ResponseViewerFriendListItem
import org.joyroom.carespoon.databinding.ItemFriendListBinding

class ViewerFriendsAdapter : RecyclerView.Adapter<ViewerFriendsAdapter.FriendsViewHolder>() {
    private var listener: ((ResponseViewerFriendListItem, Int) -> Unit)? = null
    private var friendList = emptyList<ResponseViewerFriendListItem>()
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
        fun onBind(data: ResponseViewerFriendListItem){
            binding.tvFriendsName.text = data.name

            filteringForSearching(data)
            setClinkListenerOnPosition(data)
        }

        private fun filteringForSearching(data: ResponseViewerFriendListItem){
            if (data.name.startsWith(searchWord)) {
                binding.clFriendItem.visibility = View.VISIBLE
                binding.clFriendItem.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
            } else {
                binding.clFriendItem.visibility = View.GONE
                binding.clFriendItem.layoutParams.height = 0
            }
        }

        private fun setClinkListenerOnPosition(data: ResponseViewerFriendListItem){
            binding.btnDelete.setOnClickListener {
                val pos = adapterPosition
                mPosition = pos
                listener?.invoke(data, mPosition)
            }
        }
    }

    fun setOnItemClickListener(listener: ((ResponseViewerFriendListItem, Int) -> Unit)?) {
        this.listener = listener
    }

    fun setSearchWord(text: String) {
        searchWord = text
        notifyDataSetChanged()
    }

    fun setFriendList(friendList: List<ResponseViewerFriendListItem>) {
        this.friendList = friendList.sortedBy { it.name }
        notifyDataSetChanged()
    }
}

