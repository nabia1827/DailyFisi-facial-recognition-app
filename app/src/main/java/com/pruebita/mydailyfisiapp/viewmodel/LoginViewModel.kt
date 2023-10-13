package com.pruebita.mydailyfisiapp.viewmodel

import android.util.Patterns
import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay

class LoginViewModel : ViewModel() {
    private val _userErrors = listOf("Campo vacio", "Usuario no valido", "Usuario no registrado")
    private val _passwordErrors = listOf("Campo vacio", "Contraseña incorrecta")

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _isValidationUserCorrect = MutableLiveData<Boolean>()
    val isValidationUserCorrect: LiveData<Boolean> = _isValidationUserCorrect

    private val _isValidationPassCorrect = MutableLiveData<Boolean>()
    val isValidationPassCorrect: LiveData<Boolean> = _isValidationPassCorrect

    private val _txtValidationUserCorrect = MutableLiveData<String>()
    val txtValidationUserCorrect: LiveData<String> = _txtValidationUserCorrect

    private val _txtValidationPassCorrect = MutableLiveData<String>()
    val txtValidationPassCorrect: LiveData<String> = _txtValidationPassCorrect

    private val _isFirstLogin = MutableLiveData<Boolean>()
    val isFirstLogin: LiveData<Boolean> = _isFirstLogin


    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
    }



    private fun isValidPassword(password: String): Boolean {
        if(password =="123"){
            _txtValidationPassCorrect.value = ""
            return true

        }else{
            _txtValidationPassCorrect.value =_passwordErrors[1]
            return false
        }


    }

    private fun isValidEmail(email: String): Boolean {
        if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            if(email =="taylor@gmail.com"){
                _txtValidationUserCorrect.value = ""
                return true
            }
            else{
                _txtValidationUserCorrect.value = _userErrors[2]
            }
        }
        else{
            _txtValidationUserCorrect.value = _userErrors[1]
        }
        return false
    }

    fun onLoginSelected(): Boolean {
        val email = _email.value
        val password = _password.value

        if (email != null && password != null) {
            _isValidationUserCorrect.value = isValidEmail(email)
            _isValidationPassCorrect.value = isValidPassword(password)
            return _isValidationUserCorrect.value == true && _isValidationPassCorrect.value == true
        } else {
            if(email == null)
                _txtValidationUserCorrect.value = _userErrors[0]
            if(password == null)
                _txtValidationPassCorrect.value = _passwordErrors[0]
            _isValidationUserCorrect.value = false
            _isValidationPassCorrect.value = false
            return false
        }
    }

}