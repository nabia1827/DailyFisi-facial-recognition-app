package com.pruebita.mydailyfisiapp.data.model.domain

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
    var imageUser: String = "",
    val sessionActive: Boolean = false,  // (1) is in app (active) (0) inactive
    val userActive: Boolean = true, // (1) user in with facial register (0)no facial register
)