package org.joyroom.carespoon.data.remote.request.user

data class RequestRegisterUser(
    val name: String,
    val email: String,
    val role: String
)