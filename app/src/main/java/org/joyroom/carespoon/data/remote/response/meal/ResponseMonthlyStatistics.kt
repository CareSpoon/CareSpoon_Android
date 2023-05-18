package org.joyroom.carespoon.data.remote.response.meal

data class ResponseMonthlyStatistics(
    val meal_Kcal: Double,
    val meal_Carbon: Double,
    val meal_Fat: Double,
    val meal_Protein: Double,
    val eatMonth: String
    )