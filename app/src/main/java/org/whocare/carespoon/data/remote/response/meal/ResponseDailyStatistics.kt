package org.whocare.carespoon.data.remote.response.meal

data class ResponseDailyStatistics(
    val meal_Kcal: Double,
    val meal_Carbon: Double,
    val meal_Fat: Double,
    val meal_Protein: Double,
    val meal_na: Double,
    val meal_cal: Double,
    val meal_fe: Double,
    val eatDate: String,
    )