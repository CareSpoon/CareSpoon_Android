package org.joyroom.carespoon.data.api

import org.joyroom.carespoon.data.remote.request.friend.RequestAddFriend
import org.joyroom.carespoon.data.remote.response.friend.ResponseSearchUser
import org.joyroom.carespoon.data.remote.response.friend.ResponseSeniorFriendList
import org.joyroom.carespoon.data.remote.response.friend.ResponseViewerFriendList
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface FriendService {
    @GET("user/{uuid}")
    suspend fun searchUser(
    ): ResponseSearchUser

    @POST("friendlist")
    suspend fun addFriend(
        @Body usersId: RequestAddFriend
    )

    @GET("friendsof/senior/{uuid}")
    suspend fun getSeniorFriendList(
    ): ResponseSeniorFriendList

    @GET("friendsof/viewr/{uuid}")
    suspend fun getViewerFriendList(
    ): ResponseViewerFriendList

    @DELETE("friendlist/remove/{seniorId}/{viewerId}")
    suspend fun deleteFriend(
    )
}