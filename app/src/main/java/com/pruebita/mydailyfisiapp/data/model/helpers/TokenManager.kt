package com.pruebita.mydailyfisiapp.data.model.helpers

import android.content.Context
import android.content.SharedPreferences
import com.pruebita.mydailyfisiapp.data.model.domain.User

class TokenManager(private val context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("TokenData", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        val editor = sharedPreferences.edit()
        editor.putString("token", token)
        editor.apply()
    }

    fun getToken(): String {
        val token = sharedPreferences.getString("token", "")
        return token.toString()
    }

    fun deleteToken() {
        val editor = sharedPreferences.edit()
        editor.remove("token")
        editor.apply()
    }
}