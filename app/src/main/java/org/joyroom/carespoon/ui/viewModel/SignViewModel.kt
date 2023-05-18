package org.joyroom.carespoon.ui.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.joyroom.carespoon.data.CareSpoonSharedPreferences

class SignViewModel(application: Application) : AndroidViewModel(application) {

    // 로그인 서버통신 코드 짤 때 리스폰스로 받아온 토큰값 넣어주기
    fun saveAccessToken(token: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            CareSpoonSharedPreferences.setAccessToken(token)
        }
    }


    fun saveUserName(token: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            CareSpoonSharedPreferences.setUserName(token)
        }
    }
}