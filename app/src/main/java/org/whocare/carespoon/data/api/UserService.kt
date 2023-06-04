package org.whocare.carespoon.data.api

import org.whocare.carespoon.data.remote.request.user.RequestRegisterUser
import org.whocare.carespoon.data.remote.response.user.ResponseRegisterUser
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("user")
    suspend fun registerUser(
        @Body user: RequestRegisterUser
    ): ResponseRegisterUser
}