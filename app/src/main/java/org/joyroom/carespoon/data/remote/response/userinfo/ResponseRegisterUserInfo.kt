package org.joyroom.carespoon.data.remote.response.userinfo

data class ResponseRegisterUserInfo(
    val age: Int,
    val height: Double,
    val id: Int,
    val metabolicRate: Double,
    val sex: Int,
    val user: User,
    val weight: Double
)

data class User(
    val email: String,
    val id: Int,
    val name: String,
    val role: String,
    val uuid: String
)