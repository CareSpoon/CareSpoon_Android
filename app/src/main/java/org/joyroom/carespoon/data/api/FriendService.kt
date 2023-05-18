package org.joyroom.carespoon.data.api

import org.joyroom.carespoon.data.remote.request.friend.RequestAddFriend
import org.joyroom.carespoon.data.remote.response.friend.ResponseSearchUser
import org.joyroom.carespoon.data.remote.response.friend.ResponseSeniorFriendList
import org.joyroom.carespoon.data.remote.response.friend.ResponseViewerFriendList
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FriendService {
    @GET("user/{uuid}")
    suspend fun searchUser(
        @Path("uuid") uuid: String
    ): ResponseSearchUser

    @POST("friendlist")
    suspend fun addFriend(
        @Body usersId: RequestAddFriend
    )

    @GET("friendsof/senior/{uuid}")
    suspend fun getSeniorFriendList(
        @Path("uuid") uuid: String
    ): ResponseSeniorFriendList

    @GET("friendsof/viewr/{uuid}")
    suspend fun getViewerFriendList(
        @Path("uuid") uuid: String
    ): ResponseViewerFriendList

    @DELETE("friendlist/remove/{seniorId}/{viewerId}")
    suspend fun deleteFriend(
        @Path("seniorId") seniorId: String,
        @Path("viewerId") viewerId: String
    )
}