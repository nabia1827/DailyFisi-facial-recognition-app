package com.pruebita.mydailyfisiapp.data.repository.interfaces

import com.pruebita.mydailyfisiapp.data.model.domain.Event
import com.pruebita.mydailyfisiapp.data.model.domain.ProfileUser
import com.pruebita.mydailyfisiapp.data.model.domain.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Query

interface ApiService {
    //Event controller
    @GET("api/event")
    suspend fun listAllTodayEvents(@Header("Authorization") authorization: String): Response<MutableList<Event>>

    //User controller
    @GET("api/user")
    suspend fun getUser(
        @Header("Authorization") authorization: String,
        @Query("pUser") pUser: String,
        @Query("pPassword") pPassword: String
    ): Response<User>

    @GET("api/user")
    suspend fun validateUser(
        @Header("Authorization") authorization: String,
        @Query("pUser") pUser: String,
        @Query("pPassword") pPassword: String,
        @Query("validate") validate: Int

    ): Response<Int>

    @GET("api/user")
    suspend fun getUserFromGoogle(
        @Header("Authorization") authorization: String,
        @Query("userName") userName: String
    ): Response<User>

    @PUT("api/user")
    suspend fun updateImageUser(
        @Header("Authorization") authorization: String,
        @Body profileUser: ProfileUser
    ): Response<Int>

    @PUT("api/user")
    suspend fun setActiveSession(
        @Header("Authorization") authorization: String,
        @Query("idUser") idUser: Int,
        @Query("sessionActive") sessionActive: Boolean
    ): Response<Int>

    @GET("api/user")
    suspend fun getUserActive(
        @Header("Authorization") authorization: String,
        @Query("idUser") idUser: Int,
    ): Response<Boolean>

    @PUT("api/user")
    suspend fun setUserActive(
        @Header("Authorization") authorization: String,
        @Query("idUser") idUser: Int,
        @Query("sessionActive") userActive: Boolean,
        @Query("active") active: Int
    ): Response<Int>



}