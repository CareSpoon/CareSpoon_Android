package org.whocare.carespoon.ui.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import org.whocare.carespoon.data.api.RetrofitBuilder
import org.whocare.carespoon.data.remote.response.meal.ResponseDayMealList
import org.whocare.carespoon.data.remote.response.meal.ResponsePostMeal
import org.whocare.carespoon.util.Event
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

    private val _showDoneToast = MutableLiveData<Event<Boolean>>()
    val showDoneToast: LiveData<Event<Boolean>> = _showDoneToast

    fun requestMealList(uuid: String, date: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            _mealList.postValue(
                RetrofitBuilder.mealService.getDayMealList(uuid, date)
            )
        }catch (e: HttpException){

        }
    }

    fun requestUploadPhoto(image: MultipartBody.Part?, uuid: String, tag: String) =
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _mealInfo.postValue(RetrofitBuilder.mealService.postMeal(image, uuid, tag))
                _showDoneToast.postValue(Event(true))
            } catch (e: HttpException) {
                _showErrorToast.postValue(Event(true))
            }
        }

}