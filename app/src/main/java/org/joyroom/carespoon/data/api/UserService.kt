package org.joyroom.carespoon.data.api

import okhttp3.MultipartBody
import org.joyroom.carespoon.data.remote.request.user.RequestRegisterUser
import org.joyroom.carespoon.data.remote.response.meal.ResponsePostMeal
import org.joyroom.carespoon.data.remote.response.user.ResponseRegisterUser
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Part

interface UserService {
    @POST("user")
    suspend fun registerUser(
        @Body user: RequestRegisterUser
    ): ResponseRegisterUser
}