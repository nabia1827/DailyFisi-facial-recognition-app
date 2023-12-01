package com.pruebita.mydailyfisiapp.data.repository.interfaces

import com.pruebita.mydailyfisiapp.data.model.domain.TokenResponse
import com.pruebita.mydailyfisiapp.data.model.domain.UsuariosApi
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/auth")
    fun auth(@Body usuariosApi: UsuariosApi): Response<TokenResponse>
}