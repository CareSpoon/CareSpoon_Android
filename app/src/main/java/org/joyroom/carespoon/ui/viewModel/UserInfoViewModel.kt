package org.joyroom.carespoon.ui.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.joyroom.carespoon.data.api.RetrofitBuilder
import org.joyroom.carespoon.data.remote.response.userinfo.ResponseRegisterUserInfo
import org.joyroom.carespoon.data.remote.response.userinfo.ResponseUserInfo

class UserInfoViewModel (application: Application) : AndroidViewModel(application) {
    private val _userInfo = MutableLiveData<ResponseUserInfo>()
    val userInfo: LiveData<ResponseUserInfo>
        get() = _userInfo

    fun requestUserInfo(userId:String) = viewModelScope.launch(Dispatchers.IO) {
        _userInfo.postValue(
            RetrofitBuilder.userInfoService.getUserInfo(userId)
        )
    }
}