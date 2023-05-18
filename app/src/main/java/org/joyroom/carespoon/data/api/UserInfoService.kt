package org.joyroom.carespoon.data.api

import org.joyroom.carespoon.data.remote.request.userinfo.RequestRegisterUserInfo
import org.joyroom.carespoon.data.remote.response.userinfo.ResponseRegisterUserInfo
import org.joyroom.carespoon.data.remote.response.userinfo.ResponseUserInfo
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserInfoService {
    @POST("userinfo")
    suspend fun registerUserInfo(
        @Body userInfo: RequestRegisterUserInfo
    ): ResponseRegisterUserInfo

    @GET("userinfo/{userId}")
    suspend fun getUserInfo(
    ): ResponseUserInfo
}