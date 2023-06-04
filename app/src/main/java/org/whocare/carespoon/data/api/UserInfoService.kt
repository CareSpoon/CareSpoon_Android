package org.whocare.carespoon.data.api

import org.whocare.carespoon.data.remote.request.userinfo.RequestRegisterUserInfo
import org.whocare.carespoon.data.remote.response.userinfo.ResponseRegisterUserInfo
import org.whocare.carespoon.data.remote.response.userinfo.ResponseUserInfo
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserInfoService {
    @POST("userinfo")
    suspend fun registerUserInfo(
        @Body userInfo: RequestRegisterUserInfo
    ): ResponseRegisterUserInfo

    @GET("userinfo/{userId}")
    suspend fun getUserInfo(
        @Path("userId") userId: String
    ): ResponseUserInfo
}