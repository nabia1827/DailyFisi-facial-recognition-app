package com.pruebita.mydailyfisiapp.data.model.domain

data class UsuariosApi(
    val Codigo:Int,
    val UserName:String,
    val Clave:ByteArray?,
    val Clavetxt:String,
    val Nombre:String,
    val Rol:String,
)