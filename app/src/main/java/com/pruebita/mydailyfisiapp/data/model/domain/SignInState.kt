package com.pruebita.mydailyfisiapp.data.model.domain

import java.lang.Error

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)
