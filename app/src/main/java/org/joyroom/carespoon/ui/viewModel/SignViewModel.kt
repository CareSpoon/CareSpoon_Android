package org.joyroom.carespoon.ui.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.joyroom.carespoon.data.CareSpoonSharedPreferences
import org.joyroom.carespoon.data.api.RetrofitBuilder
import org.joyroom.carespoon.data.remote.request.user.RequestRegisterUser
import org.joyroom.carespoon.data.remote.response.user.ResponseRegisterUser

class SignViewModel(application: Application) : AndroidViewModel(application) {
    private val _uuid = MutableLiveData<ResponseRegisterUser>()
    val uuid: LiveData<ResponseRegisterUser>
        get() = _uuid

    fun saveUserName(token: String?){
        viewModelScope.launch(Dispatchers.IO) {
            CareSpoonSharedPreferences.setUserName(token)
        }
    }

    fun saveUserEmail(token: String?){
        viewModelScope.launch(Dispatchers.IO) {
            CareSpoonSharedPreferences.setUserEmail(token)
        }
    }

    fun requestSign(name:String, email:String, role:String) = viewModelScope.launch(Dispatchers.IO) {
        _uuid.postValue(
            RetrofitBuilder.userService.registerUser(
                RequestRegisterUser(name, email, role)
            )
        )
    }

    fun postUserInfo(){

    }
}