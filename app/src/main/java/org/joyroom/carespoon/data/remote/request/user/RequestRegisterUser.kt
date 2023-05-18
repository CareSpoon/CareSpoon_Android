package org.joyroom.carespoon.data.remote.request.user

data class RequestRegisterUser(
    val email: String,
    val name: String,
    val role: String
)