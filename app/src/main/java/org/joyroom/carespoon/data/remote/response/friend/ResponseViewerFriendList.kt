package org.joyroom.carespoon.data.remote.response.friend

class ResponseViewerFriendList : ArrayList<ResponseViewerFriendListItem>()
data class ResponseViewerFriendListItem(
    val name: String,
    val uuid: String
)