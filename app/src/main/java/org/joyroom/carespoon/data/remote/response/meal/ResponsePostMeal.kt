package org.joyroom.carespoon.data.remote.response.meal

data class ResponsePostMeal(
    val id: Int,
    val meal_Kcal: Double,
    val meal_Carbon: Double,
    val meal_Fat: Double,
    val meal_Protein: Double,
    val eatDate: String,
    val eatMonth: String,
    val eatTime: String,
    val tag: String,
    val imageUrl: String
    )