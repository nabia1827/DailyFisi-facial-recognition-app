package com.pruebita.mydailyfisiapp.data.model

data class User(
    val idUser: Int = 0,
    val idRol: Int = 0,
    val user: String = "",
    val password: String = "",
    val names: String = "unknown",
    val firstLastName: String = "",
    val secondLastName: String = "",
    val email: String = "",
    val cellphone: String = "",
    val imageUser: String = "",
    val idFacialIdentity: String = "",
    val sessionActive: Boolean = false,
    val userActive: Boolean = false,
)