package com.pruebita.mydailyfisiapp.data.repository.repositories

import com.pruebita.mydailyfisiapp.data.model.User
import com.pruebita.mydailyfisiapp.data.repository.interfaces.UserRepository

class UserRepositoryImpl: UserRepository {
    override fun getUser(email:String, password:String): User {
        return User(
            idUser = 1,
            idRol = 1,
            names = "Lucia Alejandra",
            firstLastName = "Rivera",
            secondLastName = "Benites",
            user = "lucia.rivera",
            password = password,
            email = email,
            cellphone = "950415842",
            imageUser = "lucia_rivera",
            idFacialIdentity = "lucia_rivera_fi",
            sessionActive = true,
            userActive = true,

        )
    }


}