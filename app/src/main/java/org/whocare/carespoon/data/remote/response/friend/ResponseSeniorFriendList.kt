package org.whocare.carespoon.data.remote.response.friend

class ResponseSeniorFriendList : ArrayList<ResponseSeniorFriendListItem>()
data class ResponseSeniorFriendListItem(
    val name: String,
    val uuid: String
)