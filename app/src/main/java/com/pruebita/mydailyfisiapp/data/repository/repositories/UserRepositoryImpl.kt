package com.pruebita.mydailyfisiapp.data.repository.repositories

import com.pruebita.mydailyfisiapp.data.model.domain.ProfileUser
import com.pruebita.mydailyfisiapp.data.model.domain.User
import com.pruebita.mydailyfisiapp.data.model.domain.UserFromGmail
import com.pruebita.mydailyfisiapp.data.repository.interfaces.ApiService
import com.pruebita.mydailyfisiapp.data.repository.interfaces.UserRepository
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

class UserRepositoryImpl(private val apiService: ApiService): UserRepository {

    override suspend fun getUser(token:String, email:String, password:String): User? {
        val authorizationHeader = "Bearer $token"
        val pUser = email.substringBefore("@")
        var user :User?= User()
        try {
            val response = apiService.getUser(authorizationHeader,pUser,password)
            if (response.isSuccessful) {
                user = response.body()
                println("getUser: ${user?.names}")
            } else {
                println("Error api getUser: ${response.code()}")
            }
        } catch (e: Exception) {
            println("Error api getUser en catch: $e" )
        }
        return user
    }

    override suspend fun getUserFromGoogle(token:String,userGmail: UserFromGmail): User? {
        val authorizationHeader = "Bearer $token"

        val userName = userGmail.email?:"".substringBefore("@")
        var user :User?= User()
        try {
            val response = apiService.getUserFromGoogle(authorizationHeader,userName)
            if (response.isSuccessful) {
                user = response.body()
                println("getUserFromGoogle: ${user?.names}")
            } else {
                println("Error api getUserFromGoogle: ${response.code()}")
            }
        } catch (e: Exception) {
            println("Error api getUserFromGoogle en catch: $e" )
        }
        return user
    }

    override suspend fun updateImageUser(token:String, user: User) {
        val authorizationHeader = "Bearer $token"
        try {
            val response = apiService.updateImageUser(authorizationHeader, ProfileUser(user.idUser,user.imageUser))
            if (response.isSuccessful) {
                val i = response.body()
                println("getUserFromGoogle: $i")
            } else {
                println("Error api getUserFromGoogle: ${response.code()}")
            }
        } catch (e: Exception) {
            println("Error api getUserFromGoogle en catch: $e" )
        }
    }

    override suspend fun setActiveSession(token: String,id: Int,isActive: Boolean) {
        val authorizationHeader = "Bearer $token"
        try {
            val response = apiService.setActiveSession(authorizationHeader,id,isActive)
            if (response.isSuccessful) {
                val i = response.body()
                println("setActiveSession: $i")
            } else {
                println("Error api setActiveSession: ${response.code()}")
            }
        } catch (e: Exception) {
            println("Error api setActiveSession en catch: $e" )
        }
    }

    override suspend fun validateUser(token: String, email: String, password: String): Int {
        val authorizationHeader = "Bearer $token"
        val pUser = email.substringBefore("@")
        var i = 0
        try {
            val response = apiService.validateUser(authorizationHeader,pUser,password,0)
            if (response.isSuccessful) {
                i = response.body()?:0
                println("getUser: $i")
            } else {
                println("Error api getUser: ${response.code()}")
            }
        } catch (e: Exception) {
            println("Error api getUser en catch: $e" )
        }
        return i
    }

    override suspend fun getUserActive(token: String,idUser: Int): Boolean {
        val authorizationHeader = "Bearer $token"
        var isActive:Boolean? = false
        try {
            val response = apiService.getUserActive(authorizationHeader,idUser)
            if (response.isSuccessful) {
                isActive = response.body()
                println("getUserActive: $isActive")
            } else {
                println("Error api getUserActive: ${response.code()}")
            }
        } catch (e: Exception) {
            println("Error api getUserActive en catch: $e" )
        }
        return isActive == true
    }
    override suspend fun setUserActive(token: String,idUser: Int, userActive: Boolean) {
        val authorizationHeader = "Bearer $token"
        var isActive:Int? = 0
        try {
            val response = apiService.setUserActive(authorizationHeader,idUser, userActive,0)
            if (response.isSuccessful) {
                isActive = response.body()
                println("setUserActive: $isActive")
            } else {
                println("Error api setUserActive: ${response.code()}")
            }
        } catch (e: Exception) {
            println("Error api setUserActive en catch: $e" )
        }

    }

}