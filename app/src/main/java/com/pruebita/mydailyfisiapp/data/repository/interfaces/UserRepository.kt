package com.pruebita.mydailyfisiapp.data.repository.interfaces

import com.pruebita.mydailyfisiapp.data.model.User
import com.pruebita.mydailyfisiapp.data.model.UserFromGmail

interface UserRepository {
    fun getUser(user:String, password:String): User
    fun getUserFromGoogle(user: UserFromGmail): User

    fun updateUser(user: User): Unit

    fun setActiveSession(id:Int, isActive: Boolean):Unit
}