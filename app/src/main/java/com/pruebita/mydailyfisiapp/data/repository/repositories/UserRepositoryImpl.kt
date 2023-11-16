package com.pruebita.mydailyfisiapp.data.repository.repositories

import com.pruebita.mydailyfisiapp.data.model.domain.User
import com.pruebita.mydailyfisiapp.data.model.domain.UserFromGmail
import com.pruebita.mydailyfisiapp.data.repository.interfaces.UserRepository

class UserRepositoryImpl: UserRepository {
    override fun getUser(email:String, password:String): User {
        return User(
            idUser = 1989,
            idRol = 1,
            names = "Kevin Miguel",
            firstLastName = "Ortiz",
            secondLastName = "Abanto",
            user = "lucia.rivera",
            password = password,
            email = email,
            cellphone = "950415842",
            imageUser = "https://firebasestorage.googleapis.com/v0/b/dailyfisiapp.appspot.com/o/users%2Fprofiles%2Fuser_1.png?alt=media&token=a585563f-5523-453b-8938-3ec23da43615&_gl=1*1ixammm*_ga*ODYxODYyNzM3LjE2OTg5ODIwNDg.*_ga_CW55HF8NVT*MTY5OTA4MTk4MC45LjEuMTY5OTA4NDY3OC4xMC4wLjA",
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