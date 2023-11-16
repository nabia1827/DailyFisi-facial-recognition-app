package com.pruebita.mydailyfisiapp.data.model.domain

data class SignInResult(
    val data: UserFromGmail?,
    val errorMessage:String?
)