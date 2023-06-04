package org.whocare.carespoon.data.api

import okhttp3.MultipartBody
import org.whocare.carespoon.data.remote.response.meal.ResponseDailyStatistics
import org.whocare.carespoon.data.remote.response.meal.ResponseDayMealList
import org.whocare.carespoon.data.remote.response.meal.ResponseMonthlyStatistics
import org.whocare.carespoon.data.remote.response.meal.ResponsePostMeal
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface MealService {
    @Multipart
    @POST("onemeal")
    suspend fun postMeal(
        @Part image: MultipartBody.Part?,
        @Query("userId") userId: String,
        @Query("tag") tag: String
    ): ResponsePostMeal

    @GET("dailymeals/{userId}/{date}")
    suspend fun getDayMealList(
        @Path("userId") userId: String,
        @Path("date") date: String
        ): ResponseDayMealList

    @GET("dailystatistics/{userId}/{date}")
    suspend fun getDailyStatistics(
        @Path("userId") userId: String,
        @Path("date") date: String
    ): ResponseDailyStatistics

    @GET("monthlystatistics/{userId}/{month}")
    suspend fun getMonthlyStatistics(
        @Path("userId") userId: String,
        @Path("month") month: String
    ): ResponseMonthlyStatistics
}