package org.whocare.carespoon.data.remote.response.friend

data class ResponseSearchUser(
    val id: Int,
    val uuid: String,
    val email: String,
    val name: String,
    val role: String
)