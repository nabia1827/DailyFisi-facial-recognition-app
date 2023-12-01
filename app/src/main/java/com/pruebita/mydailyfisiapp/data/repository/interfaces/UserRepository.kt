package com.pruebita.mydailyfisiapp.data.repository.interfaces

import com.pruebita.mydailyfisiapp.data.model.domain.User
import com.pruebita.mydailyfisiapp.data.model.domain.UserFromGmail

interface UserRepository {
    fun getUser(token:String,user:String, password:String): User?
    fun getUserFromGoogle(token:String,user: UserFromGmail): User?

    fun updateImageUser(token:String,user: User): Unit

    fun setActiveSession(token: String,id:Int, isActive: Boolean):Unit

    fun validateUser(token:String,user:String, password:String): Int

    fun getUserActive(token:String,idUser:Int): Boolean

    fun setUserActive(token:String,idUser:Int, userActive:Boolean): Unit



}