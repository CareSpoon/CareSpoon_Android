package org.joyroom.carespoon.data.remote.response.userinfo

data class ResponseRegisterUserInfo(
    val id: Int,
    val age: Int,
    val sex: Int,
    val height: Double,
    val weight: Double,
    val metabolicRate: Double,
    val user: User
)

data class User(
    val id: Int,
    val uuid: String,
    val email: String,
    val name: String,
    val role: String
)