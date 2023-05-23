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
import org.joyroom.carespoon.data.remote.response.meal.ResponseDailyStatistics
import org.joyroom.carespoon.data.remote.response.meal.ResponseDayMealList
import org.joyroom.carespoon.data.remote.response.meal.ResponsePostMeal
import org.joyroom.carespoon.util.Event
import retrofit2.HttpException

class StatisticsViewModel(application: Application) : AndroidViewModel(application) {
    private val _statisticData = MutableLiveData<ResponseDailyStatistics>()
    val statisticData: LiveData<ResponseDailyStatistics>
        get() = _statisticData

    private val _noData = MutableLiveData<Event<Boolean>>()
    val noData: LiveData<Event<Boolean>> = _noData

    private val _withData = MutableLiveData<Event<Boolean>>()
    val withData: LiveData<Event<Boolean>> = _withData

    fun requestDayList(uuid: String, date: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            _statisticData.postValue(
                RetrofitBuilder.mealService.getDailyStatistics(uuid, date)
            )
            _withData.postValue(Event(true))
        } catch (e: HttpException){
            _noData.postValue(Event(true))
        }
    }

}