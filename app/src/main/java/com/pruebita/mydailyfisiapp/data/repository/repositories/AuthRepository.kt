package com.pruebita.mydailyfisiapp.data.repository.repositories

import com.pruebita.mydailyfisiapp.data.model.domain.TokenResponse
import com.pruebita.mydailyfisiapp.data.model.domain.UsuariosApi
import com.pruebita.mydailyfisiapp.data.repository.interfaces.AuthService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(private val authService: AuthService) {
    fun auth(credentials: UsuariosApi): TokenResponse {
        return TokenResponse("","")
    }
}