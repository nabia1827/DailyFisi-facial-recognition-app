package com.pruebita.mydailyfisiapp.data.model.domain

data class UserFromGmail(
    val userId: String,
    val userName: String?,
    val profilePictureUrl: String?,
    val email: String?
)