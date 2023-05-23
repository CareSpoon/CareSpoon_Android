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
import org.joyroom.carespoon.data.remote.response.meal.ResponseMonthlyStatistics
import org.joyroom.carespoon.data.remote.response.meal.ResponsePostMeal
import org.joyroom.carespoon.util.Event
import retrofit2.HttpException

class StatisticsViewModel(application: Application) : AndroidViewModel(application) {
    private val _dailyStatisticData = MutableLiveData<ResponseDailyStatistics>()
    val dailyStatisticData: LiveData<ResponseDailyStatistics>
        get() = _dailyStatisticData

    private val _noDayData = MutableLiveData<Event<Boolean>>()
    val noDayData: LiveData<Event<Boolean>> = _noDayData

    private val _withDayData = MutableLiveData<Event<Boolean>>()
    val withDayData: LiveData<Event<Boolean>> = _withDayData

    private val _monthlyStatisticData = MutableLiveData<ResponseMonthlyStatistics>()
    val monthlyStatisticData: LiveData<ResponseMonthlyStatistics>
        get() = _monthlyStatisticData

    private val _noMonthData = MutableLiveData<Event<Boolean>>()
    val noMonthData: LiveData<Event<Boolean>> = _noMonthData

    private val _withMonthData = MutableLiveData<Event<Boolean>>()
    val withMonthData: LiveData<Event<Boolean>> = _withMonthData

    fun requestDayList(uuid: String, date: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            _dailyStatisticData.postValue(
                RetrofitBuilder.mealService.getDailyStatistics(uuid, date)
            )
            _withDayData.postValue(Event(true))
        } catch (e: HttpException){
            _noDayData.postValue(Event(true))
        }
    }

    fun requestMonthList(uuid: String, month: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            _monthlyStatisticData.postValue(
                RetrofitBuilder.mealService.getMonthlyStatistics(uuid, month)
            )
            _withMonthData.postValue(Event(true))
        } catch (e: HttpException){
            _noMonthData.postValue(Event(true))
        }
    }

}