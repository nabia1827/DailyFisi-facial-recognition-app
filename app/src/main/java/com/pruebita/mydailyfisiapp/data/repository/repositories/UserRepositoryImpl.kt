package com.pruebita.mydailyfisiapp.data.repository.repositories

import com.pruebita.mydailyfisiapp.data.model.User
import com.pruebita.mydailyfisiapp.data.model.UserFromGmail
import com.pruebita.mydailyfisiapp.data.repository.interfaces.UserRepository

class UserRepositoryImpl: UserRepository {
    override fun getUser(email:String, password:String): User {
        return User(
            idUser = 1,
            idRol = 1,
            names = "Lucia",
            firstLastName = "Rivera",
            secondLastName = "Benites",
            user = "lucia.rivera",
            password = password,
            email = email,
            cellphone = "950415842",
            imageUser = "https://firebasestorage.googleapis.com/v0/b/dailyfisiapp.appspot.com/o/profiles%2Fusers%2Fuser_1?alt=media&token=aacb20d6-53da-4950-99cf-8b87aa7230d1&_gl=1*jfgqh8*_ga*ODYxODYyNzM3LjE2OTg5ODIwNDg.*_ga_CW55HF8NVT*MTY5OTA0NjMyNy41LjEuMTY5OTA1NDE1NS41Ny4wLjA",
            idFacialIdentity = "lucia_rivera_fi",
            sessionActive = true,
            userActive = true,
        )
    }

    override fun getUserFromGoogle(user: UserFromGmail): User {
        val palabras = user.userName.toString().split(" ")

        return User(
            idUser = 1,
            idRol = 1,
            names = palabras[0] + " " + palabras[1] ,
            firstLastName = "Perez" ,
            secondLastName = "Diaz",
            user = "lucia.rivera",
            password = "123456",
            email = user.email.toString(),
            cellphone = "950415842",
            imageUser = user.profilePictureUrl.toString(),
            idFacialIdentity = "lucia_rivera_fi",
            sessionActive = true,
            userActive = true,
        )
    }

    override fun updateUser(user: User) {
        //Go to API
    }

    override fun setActiveSession(id: Int,isActive: Boolean) {
        //Nothing for a while
    }

}