package com.pruebita.mydailyfisiapp.data.repository.repositories

import com.pruebita.mydailyfisiapp.data.model.domain.User
import com.pruebita.mydailyfisiapp.data.model.domain.UserFromGmail
import com.pruebita.mydailyfisiapp.data.repository.interfaces.UserRepository

class UserRepositoryImpl: UserRepository {
    override fun getUser(email:String, password:String): User {
        if(email == "nabia.pachas@unmsm.edu.pe"){
            return User(
                idUser = 1,
                idRol = 1,
                names = "Nabia Jasmin",
                firstLastName = "Pachas",
                secondLastName = "Lopez",
                user = "nabia.pachas",
                password = password,
                email = "nabia.pachas@unmsm.edu.pe",
                cellphone = "950415842",
                imageUser = "https://firebasestorage.googleapis.com/v0/b/dailyfisiapp.appspot.com/o/users%2Fprofiles%2Fuser_1.jpg?alt=media&token=8fa61ee1-f687-4e43-8cab-f799bfd58f36",
                idFacialIdentity = "nabia_pachas_fi",
                sessionActive = true,
                userActive = true,
            )
        }else{
            return User(
                idUser = 2,
                idRol = 1,
                names = "Kevin Miguel",
                firstLastName = "Ortiz",
                secondLastName = "Abanto",
                user = "kevinmiguel.ortiz",
                password = password,
                email = "kevinmiguel.ortiz@unmsm.edu.pe",
                cellphone = "950415842",
                imageUser = "https://firebasestorage.googleapis.com/v0/b/dailyfisiapp.appspot.com/o/users%2Fprofiles%2Fuser_2.jpg?alt=media&token=4fbbf795-91fe-4e3b-a738-2afcce491c2d",
                idFacialIdentity = "kevin_ortiz_fi",
                sessionActive = true,
                userActive = true,
            )
        }

    }

    override fun getUserFromGoogle(user: UserFromGmail): User {
        val palabras = user.userName.toString().split(" ")

        return User(
            idUser = 1,
            idRol = 1,
            names = "Nabia Jasmin",
            firstLastName = "Pachas",
            secondLastName = "Lopez",
            user = "nabia.pachas",
            password = "123456",
            email = "nabia.pachas@unmsm.edu.pe",
            cellphone = "950415842",
            imageUser = "https://firebasestorage.googleapis.com/v0/b/dailyfisiapp.appspot.com/o/users%2Fprofiles%2Fuser_1.jpg?alt=media&token=8fa61ee1-f687-4e43-8cab-f799bfd58f36",
            idFacialIdentity = "nabia_pachas_fi",
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