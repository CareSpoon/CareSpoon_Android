package org.whocare.carespoon.data.remote.response.meal

data class ResponseDailyStatistics(
    val meal_Kcal: Double,
    val meal_Carbon: Double,
    val meal_Fat: Double,
    val meal_Protein: Double,
    val eatDate: String
    )