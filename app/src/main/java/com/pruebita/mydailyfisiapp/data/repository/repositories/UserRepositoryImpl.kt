package com.pruebita.mydailyfisiapp.data.repository.repositories

import com.pruebita.mydailyfisiapp.data.model.domain.ProfileUser
import com.pruebita.mydailyfisiapp.data.model.domain.User
import com.pruebita.mydailyfisiapp.data.model.domain.UserFromGmail
import com.pruebita.mydailyfisiapp.data.repository.interfaces.ApiService
import com.pruebita.mydailyfisiapp.data.repository.interfaces.UserRepository
import com.pruebita.mydailyfisiapp.data.source.student1
import com.pruebita.mydailyfisiapp.data.source.student2
import com.pruebita.mydailyfisiapp.data.source.teacher1
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

class UserRepositoryImpl(private val apiService: ApiService): UserRepository {

    override fun getUser(token:String, email:String, password:String): User? {
        val authorizationHeader = "Bearer $token"
        val pUser = email.substringBefore("@")
        var user :User?= User()
        when (email) {
            "nabia.pachas@unmsm.edu.pe" -> {
                user = student1
            }
            "kevinmiguel.ortiz@unmsm.edu.pe" -> {
                user = student2
            }
            "rubendavid.robles@unmsm.edu.pe" -> {
                user = teacher1
            }
        }

        return user
    }

    override fun getUserFromGoogle(token:String,userGmail: UserFromGmail): User? {
        val authorizationHeader = "Bearer $token"
        val userName = userGmail.email?:"".substringBefore("@")

        return getUser("",userGmail.email.toString(),"123456")
    }

    override fun updateImageUser(token:String, user: User) {
        when (user.idUser) {
            1 -> {
                student1.imageUser = user.imageUser
            }
            2 -> {
                student2.imageUser = user.imageUser
            }
            3 -> {
                teacher1.imageUser = user.imageUser
            }
        }
    }

    override fun setActiveSession(token: String,id: Int,isActive: Boolean) {
        when (id) {
            1 -> {
                student1.sessionActive = isActive
            }
            2 -> {
                student2.sessionActive = isActive
            }
            3 -> {
                teacher1.sessionActive = isActive
            }
        }
    }

    override fun validateUser(token: String, email: String, password: String): Int {
        val authorizationHeader = "Bearer $token"
        val pUser = email.substringBefore("@")
        var i = 0
        when (email) {
            "nabia.pachas@unmsm.edu.pe" -> {
                i = if(password == student1.password) 1 else 0
            }
            "kevinmiguel.ortiz@unmsm.edu.pe" -> {
                i = if(password == student2.password) 1 else 0
            }
            "rubendavid.robles@unmsm.edu.pe" -> {
                i = if(password == teacher1.password) 1 else 0
            }
        }
        return i
    }

    override fun getUserActive(token: String,idUser: Int): Boolean {
        val authorizationHeader = "Bearer $token"
        var isActive:Boolean? = false
        when (idUser) {
            1 -> {
                isActive = student1.userActive
            }
            2 -> {
                isActive = student2.userActive
            }
            3 -> {
                isActive = teacher1.userActive
            }
        }
        return isActive == true
    }
    override fun setUserActive(token: String,idUser: Int, userActive: Boolean) {
        when (idUser) {
            1 -> {
                student1.userActive = userActive
            }
            2 -> {
                student2.userActive = userActive
            }
            3 -> {
                teacher1.userActive = userActive
            }
        }

    }

}