package com.pruebita.mydailyfisiapp.data.repository.interfaces

import com.pruebita.mydailyfisiapp.data.model.User

interface UserRepository {
    fun getUser(user:String, password:String): User
}