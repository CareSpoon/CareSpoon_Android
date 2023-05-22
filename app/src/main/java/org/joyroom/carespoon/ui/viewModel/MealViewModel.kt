package org.joyroom.carespoon.ui.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.joyroom.carespoon.data.api.RetrofitBuilder
import org.joyroom.carespoon.data.remote.response.friend.ResponseSearchUser
import org.joyroom.carespoon.data.remote.response.meal.ResponseDayMealList

class MealViewModel(application: Application) : AndroidViewModel(application) {
    private val _mealList = MutableLiveData<ResponseDayMealList>()
    val mealList : LiveData<ResponseDayMealList>
        get() = _mealList

    fun requestMealList(uuid: String, date: String) = viewModelScope.launch(Dispatchers.IO) {
        _mealList.postValue(
            RetrofitBuilder.mealService.getDayMealList(uuid, date)
        )
    }
}