package org.whocare.carespoon.data.remote.request.userinfo

data class RequestRegisterUserInfo(
    val userId: String,
    val birth: String,
    val sex: Int,
    val height: Double,
    val weight: Double
)