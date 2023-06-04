package org.whocare.carespoon.data.remote.response.meal

class ResponseDayMealList : ArrayList<ResponseDayMealListItem>()

data class ResponseDayMealListItem(
    val eatDate: String,
    val eatTime: String,
    val imageUrl: String,
    val meal_Carbon: Double,
    val meal_Fat: Double,
    val meal_Kcal: Double,
    val meal_Protein: Double,
    val tag: String
)