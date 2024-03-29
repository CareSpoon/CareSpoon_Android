package org.whocare.carespoon.ui.viewModel

import android.app.Application
import android.text.Editable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.whocare.carespoon.data.api.RetrofitBuilder
import org.whocare.carespoon.data.remote.request.friend.RequestAddFriend
import org.whocare.carespoon.data.remote.response.friend.ResponseSearchUser
import org.whocare.carespoon.data.remote.response.friend.ResponseSeniorFriendList
import org.whocare.carespoon.data.remote.response.friend.ResponseViewerFriendList

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


    // GET - response 만 존재
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


    // DELETE - request 도 response 도 없이 path param 만 존재
    fun requestDeleteFriend(seniorUUID: String, viewerUUID: String) = viewModelScope.launch(Dispatchers.IO) {
        RetrofitBuilder.friendService.deleteFriend(seniorUUID, viewerUUID)
    }

    // POST - request 만 존재
    fun requestAddFriend(viewerUUID: String, seniorUUID: String) = viewModelScope.launch(Dispatchers.IO) {
        RetrofitBuilder.friendService.addFriend(RequestAddFriend(viewerUUID, seniorUUID))
    }
}