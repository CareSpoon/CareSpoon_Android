package org.joyroom.carespoon.data.remote.response.userinfo

data class ResponseUserInfo(
    val height: Double,
    val age: Int,
    val weight: Double,
    val metabolicRate: Double,
    val sex: Int
)