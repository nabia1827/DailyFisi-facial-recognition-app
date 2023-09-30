package com.pruebita.mydailyfisiapp.viewmodel

import android.util.Patterns
import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay

class ForgotPasswordViewModel : ViewModel() {
    private val _emailErrors = listOf("Campo vacio", "Email no valido", "Email no registrado")
    private val _passwordErrors = listOf("Campo vacio", "Contraseña invalida","Contraseña no coincidente")

    private val _emailToSend = MutableLiveData<String>()
    val emailToSend: LiveData<String> = _emailToSend


    private val _newPassword = MutableLiveData<String>()
    val newPassword: LiveData<String> = _newPassword

    private val _newPasswordC = MutableLiveData<String>()
    val newPasswordC: LiveData<String> = _newPasswordC

    private val _code = MutableLiveData<String>()
    val code: LiveData<String> = _code

    private val _digit1 = MutableLiveData<String>()
    val digit1: LiveData<String> = _digit1

    private val _digit2 = MutableLiveData<String>()
    val digit2: LiveData<String> = _digit2

    private val _digit3 = MutableLiveData<String>()
    val digit3: LiveData<String> = _digit3

    private val _digit4 = MutableLiveData<String>()
    val digit4: LiveData<String> = _digit4

    private val _isVerifiedCode = MutableLiveData<Boolean>()
    val isVerifiedCode: LiveData<Boolean> = _isVerifiedCode


    private val _isValidationEmailCorrect = MutableLiveData<Boolean>()
    val isValidationEmailCorrect: LiveData<Boolean> = _isValidationEmailCorrect

    private val _isValidationPassCorrect = MutableLiveData<Boolean>()
    val isValidationPassCorrect: LiveData<Boolean> = _isValidationPassCorrect

    private val _isValidationPassCCorrect = MutableLiveData<Boolean>()
    val isValidationPassCCorrect: LiveData<Boolean> = _isValidationPassCCorrect

    private val _txtValidationEmailCorrect = MutableLiveData<String>()
    val txtValidationEmailCorrect: LiveData<String> = _txtValidationEmailCorrect

    private val _txtValidationPassCorrect = MutableLiveData<String>()
    val txtValidationPassCorrect: LiveData<String> = _txtValidationPassCorrect

    private val _txtValidationPassCCorrect = MutableLiveData<String>()
    val txtValidationPassCCorrect: LiveData<String> = _txtValidationPassCCorrect


    fun onEmailFieldChanged(email: String) {
        _emailToSend.value = email
    }
    fun onFourCodeChanged(d1: String,d2: String,d3: String,d4: String) {
        _digit1.value = d1
        _digit2.value = d2
        _digit3.value = d3
        _digit4.value = d4
        _code.value = d1+d2+d3+d4
    }
    fun onPassFieldChanged(newPassword: String) {
        _newPassword.value = newPassword
    }
    fun onPassCFieldChanged(newPasswordC: String) {
        _newPasswordC.value = newPasswordC
    }
    private fun isValidEmail(email: String): Boolean {
        if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            if(email =="taylor@gmail.com"){
                _txtValidationEmailCorrect.value = ""
                return true
            }
            else{
                _txtValidationEmailCorrect.value = _emailErrors[2]
            }
        }
        else{
            _txtValidationEmailCorrect.value = _emailErrors[1]
        }
        return false
    }
    fun onSendingButtonSelected(): Boolean {
        val email = _emailToSend.value

        if (email != null ) {
            _isValidationEmailCorrect.value = isValidEmail(email)
            return true
        } else {
            _txtValidationEmailCorrect.value = _emailErrors[0]
            _isValidationEmailCorrect.value = false
            return false
        }
    }
    fun onVerifyingButtonSelected(): Boolean {
        val code = _code.value

        if (code != null && code.length == 4 ) {
            _isVerifiedCode.value = isValidCode(code)
            return true
        } else {
            _isVerifiedCode.value = false
            return false
        }
    }

    private fun isValidCode(code: String): Boolean {
        return code =="1234"
    }

    private fun isValidPassword(password: String): Boolean {
        if(password.length >= 8){
            _txtValidationPassCorrect.value = ""
            return true

        }else{
            _txtValidationPassCorrect.value =_passwordErrors[1]
            return false
        }


    }

    fun onChangingButtonSelected(): Boolean {
        val newPassword = _newPassword.value
        val newPasswordC = _newPasswordC.value

        if (newPassword != null && newPasswordC!= null) {
            _isValidationPassCorrect.value = isValidPassword(newPassword)
            _isValidationPassCCorrect.value = isValidPassword(newPasswordC)
            if(_isValidationPassCorrect.value == true && _isValidationPassCCorrect.value == true){
                if(newPassword == newPasswordC){
                    return true
                }
                else{
                    _txtValidationPassCCorrect.value = _passwordErrors[2]
                    return false
                }
            }
            else{

                return false
            }

        } else {
            if(newPassword == null)
                _txtValidationPassCorrect.value = _passwordErrors[0]
            if(newPasswordC == null)
                _txtValidationPassCCorrect.value = _passwordErrors[0]
            _isValidationPassCorrect.value = false
            _isValidationPassCCorrect.value = false
            return false
        }
    }


}