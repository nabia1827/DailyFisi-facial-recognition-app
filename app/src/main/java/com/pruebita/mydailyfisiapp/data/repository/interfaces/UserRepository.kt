package com.pruebita.mydailyfisiapp.data.repository.interfaces

import com.pruebita.mydailyfisiapp.data.model.domain.User
import com.pruebita.mydailyfisiapp.data.model.domain.UserFromGmail

interface UserRepository {
    suspend fun getUser(token:String,user:String, password:String): User?
    suspend fun getUserFromGoogle(token:String,user: UserFromGmail): User?

    suspend fun updateImageUser(token:String,user: User): Unit

    suspend fun setActiveSession(token: String,id:Int, isActive: Boolean):Unit

    suspend fun validateUser(token:String,user:String, password:String): Int

    suspend fun getUserActive(token:String,idUser:Int): Boolean

    suspend fun setUserActive(token:String,idUser:Int, userActive:Boolean): Unit



}