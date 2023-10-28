package com.pruebita.mydailyfisiapp.data.model

import android.content.Context
import android.content.SharedPreferences

class UserManager(private val context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("UserData", Context.MODE_PRIVATE)

    fun saveUser(user: User) {
        val editor = sharedPreferences.edit()
        editor.putInt("idUser", user.idUser)
        editor.putInt("idRol", user.idRol)
        editor.putString("names", user.names)
        editor.putString("firstLastName", user.firstLastName)
        editor.putString("secondLastName", user.secondLastName)
        editor.putString("user", user.user)
        editor.putString("password", user.password)
        editor.putString("email", user.email)
        editor.putString("cellphone", user.cellphone)
        editor.putString("imageUser", user.imageUser)
        editor.putString("idFacialIdentity", user.idFacialIdentity)
        editor.putBoolean("sessionActive", user.sessionActive)
        editor.putBoolean("userActive", user.userActive)
        editor.apply()
    }

    fun getUser(): User? {
        val idUser = sharedPreferences.getInt("idUser", 0)
        val idRol = sharedPreferences.getInt("idRol", 0)
        val names = sharedPreferences.getString("names", "")
        val firstLastName = sharedPreferences.getString("firstLastName", "")
        val secondLastName = sharedPreferences.getString("secondLastName", "")
        val user = sharedPreferences.getString("user", "")
        val password = sharedPreferences.getString("password", "")
        val email = sharedPreferences.getString("email", "")
        val cellphone = sharedPreferences.getString("cellphone", "")
        val imageUser = sharedPreferences.getString("imageUser", "")
        val idFacialIdentity = sharedPreferences.getString("idFacialIdentity", "")
        val sessionActive = sharedPreferences.getBoolean("sessionActive", false)
        val userActive = sharedPreferences.getBoolean("userActive", false)


        return User(idUser,idRol, user!!,
            password!!,names!!,firstLastName!!,secondLastName!!,email!!,
            cellphone!!,imageUser!!,idFacialIdentity!!,sessionActive,userActive)
    }

    fun deleteUser() {
        val editor = sharedPreferences.edit()
        editor.remove("idUser")
        editor.remove("idRol")
        editor.remove("names")
        editor.remove("firstLastName")
        editor.remove("secondLastName")
        editor.remove("user")
        editor.remove("password")
        editor.remove("email")
        editor.remove("cellphone")
        editor.remove("imageUser")
        editor.remove("idFacialIdentity")
        editor.remove("sessionActive")
        editor.remove("userActive")
        editor.apply()
    }

    fun getIdRol(): Int {
        return sharedPreferences.getInt("idRol", 0)
    }
}
