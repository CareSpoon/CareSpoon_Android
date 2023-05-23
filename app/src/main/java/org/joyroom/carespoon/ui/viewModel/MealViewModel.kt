package org.joyroom.carespoon.ui.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import org.joyroom.carespoon.data.api.RetrofitBuilder
import org.joyroom.carespoon.data.remote.response.meal.ResponseDayMealList
import org.joyroom.carespoon.data.remote.response.meal.ResponsePostMeal
import org.joyroom.carespoon.util.Event
import retrofit2.HttpException

class MealViewModel(application: Application) : AndroidViewModel(application) {
    private val _mealList = MutableLiveData<ResponseDayMealList>()
    val mealList: LiveData<ResponseDayMealList>
        get() = _mealList

    private val _mealInfo = MutableLiveData<ResponsePostMeal>()
    val mealInfo: LiveData<ResponsePostMeal>
        get() = _mealInfo

    private val _showErrorToast = MutableLiveData<Event<Boolean>>()
    val showErrorToast: LiveData<Event<Boolean>> = _showErrorToast

    fun requestMealList(uuid: String, date: String) = viewModelScope.launch(Dispatchers.IO) {
        _mealList.postValue(
            RetrofitBuilder.mealService.getDayMealList(uuid, date)
        )
    }

    fun requestUploadPhoto(image: MultipartBody.Part?, uuid: String, tag: String) =
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _mealInfo.postValue(RetrofitBuilder.mealService.postMeal(image, uuid, tag))
            } catch (e: HttpException) {
                _showErrorToast.postValue(Event(true))
            }
        }

}