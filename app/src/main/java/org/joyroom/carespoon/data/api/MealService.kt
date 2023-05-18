package org.joyroom.carespoon.data.api

import okhttp3.MultipartBody
import org.joyroom.carespoon.data.remote.response.meal.ResponseDailyStatistics
import org.joyroom.carespoon.data.remote.response.meal.ResponseDayMealList
import org.joyroom.carespoon.data.remote.response.meal.ResponseMonthlyStatistics
import org.joyroom.carespoon.data.remote.response.meal.ResponsePostMeal
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface MealService {
    @Multipart
    @POST("onemeal")
    suspend fun postMeal(
        @Part file: MultipartBody.Part
    ): ResponsePostMeal

    @GET("dailymeals/{userId}/{date}")
    suspend fun getDayMealList(
    ): ResponseDayMealList

    @GET("dailystatistics/{userId}/{date}")
    suspend fun getDailyStatistics(
    ): ResponseDailyStatistics

    @GET("monthlystatistics/{userId}/{month}")
    suspend fun getMonthlyStatistics(
    ): ResponseMonthlyStatistics
}