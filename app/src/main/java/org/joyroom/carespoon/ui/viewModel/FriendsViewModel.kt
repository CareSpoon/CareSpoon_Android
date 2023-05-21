package org.joyroom.carespoon.ui.viewModel

import android.app.Application
import android.text.Editable
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.joyroom.carespoon.data.api.RetrofitBuilder
import org.joyroom.carespoon.data.remote.request.friend.RequestAddFriend
import org.joyroom.carespoon.data.remote.response.friend.ResponseSearchUser
import org.joyroom.carespoon.data.remote.response.friend.ResponseSeniorFriendList
import org.joyroom.carespoon.data.remote.response.friend.ResponseViewerFriendList

class FriendsViewModel(application: Application) : AndroidViewModel(application) {
    private val _seniorFriendList = MutableLiveData<ResponseSeniorFriendList>()
    val seniorFriendList: LiveData<ResponseSeniorFriendList>
        get() = _seniorFriendList

    private val _viewerFriendList = MutableLiveData<ResponseViewerFriendList>()
    val viewerFriendList: LiveData<ResponseViewerFriendList>
        get() = _viewerFriendList

    private val _userList = MutableLiveData<ResponseSearchUser>()
    val userList : LiveData<ResponseSearchUser>
        get() = _userList


    fun requestSeniorFriendList(uuid: String) = viewModelScope.launch(Dispatchers.IO) {
        _seniorFriendList.postValue(
            RetrofitBuilder.friendService.getSeniorFriendList(uuid)
        )
    }

    fun requestViewerFriendList(uuid: String) = viewModelScope.launch(Dispatchers.IO) {
        _viewerFriendList.postValue(
            RetrofitBuilder.friendService.getViewerFriendList(uuid)
        )
    }

    fun searchUser(uuid: Editable) = viewModelScope.launch(Dispatchers.IO) {
        _userList.postValue(
            RetrofitBuilder.friendService.searchUser(uuid.toString()).body()
        )
    }

    fun requestDeleteFriend(seniorUUID: String, viewerUUID: String) = viewModelScope.launch(Dispatchers.IO) {
        RetrofitBuilder.friendService.deleteFriend(seniorUUID, viewerUUID)
    }

    fun requestAddFriend(viewerUUID: String, seniorUUID: String) = viewModelScope.launch(Dispatchers.IO) {
        RetrofitBuilder.friendService.addFriend(RequestAddFriend(viewerUUID, seniorUUID))

    }
}