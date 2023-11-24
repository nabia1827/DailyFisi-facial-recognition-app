package com.pruebita.mydailyfisiapp.data.repository.repositories

import com.pruebita.mydailyfisiapp.data.model.domain.TokenResponse
import com.pruebita.mydailyfisiapp.data.model.domain.UsuariosApi
import com.pruebita.mydailyfisiapp.data.repository.interfaces.AuthService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(private val authService: AuthService) {
    suspend fun auth(credentials: UsuariosApi): TokenResponse {
        val response = authService.auth(credentials)
        var token:TokenResponse = TokenResponse("aa","aa")
        if (response.isSuccessful) {
            println("Api auth is successful?: ${response.code()}")
            token = response.body()?:TokenResponse("bb","bb")
        } else {
            token  =TokenResponse("cc","cc")
            println("Error authentication: ${response.errorBody().toString()}")
        }
        println("Bearer $token")
        return token
    }
}