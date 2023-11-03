package com.pruebita.mydailyfisiapp.data.repository.repositories

import com.pruebita.mydailyfisiapp.data.repository.interfaces.RolRepository

class RolRepositoryImpl: RolRepository {
    override fun getRol(id: Int): String {
        return when(id){
            1 -> "Estudiante"
            2 -> "Delegado"
            else -> "Profesor"
        }
    }
}