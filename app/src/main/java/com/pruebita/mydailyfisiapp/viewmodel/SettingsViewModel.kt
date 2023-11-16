package com.pruebita.mydailyfisiapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class SettingsViewModel {
    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _isValidationPassCorrect = MutableLiveData<Boolean>()
    val isValidationPassCorrect: LiveData<Boolean> = _isValidationPassCorrect

    private val _txtValidationPassCorrect = MutableLiveData<String>()
    val txtValidationPassCorrect: LiveData<String> = _txtValidationPassCorrect

    private val _userErrors = listOf("Campo vacio", "Usuario no valido", "Usuario no registrado")
    private val _passwordErrors = listOf("Campo vacio", "Contraseña incorrecta")


    fun onPasswordChanged(password: String) {
        _password.value = password
    }
}