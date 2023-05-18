package org.joyroom.carespoon.data.api

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    const val BASE_URL = "http://34.64.211.6:8080/"

    private val client = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
        .build()

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    private val careSpoonRetrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val friendService: FriendService = careSpoonRetrofit.create(FriendService::class.java)
    val mealService: MealService = careSpoonRetrofit.create(MealService::class.java)
    val userInfoService: UserInfoService = careSpoonRetrofit.create(UserInfoService::class.java)
    val userService: UserService = careSpoonRetrofit.create(UserService::class.java)
}