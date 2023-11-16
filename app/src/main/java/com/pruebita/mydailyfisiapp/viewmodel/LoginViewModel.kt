package com.pruebita.mydailyfisiapp.viewmodel

import android.content.Context
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pruebita.mydailyfisiapp.data.model.domain.SignInResult
import com.pruebita.mydailyfisiapp.data.model.domain.SignInState
import com.pruebita.mydailyfisiapp.data.model.domain.User
import com.pruebita.mydailyfisiapp.data.model.domain.UserFromGmail
import com.pruebita.mydailyfisiapp.data.model.helpers.UserManager
import com.pruebita.mydailyfisiapp.data.repository.repositories.UserRepositoryImpl
import com.pruebita.mydailyfisiapp.ui.navigation.AppScreens
import com.pruebita.mydailyfisiapp.ui.navigation.ItemMenu
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val context: Context) : ViewModel() {
    private val repo: UserRepositoryImpl = UserRepositoryImpl()

    private val userManager: UserManager = UserManager(context)

    private val _currentUser = MutableLiveData<User>()
    val currentUser: LiveData<User> = _currentUser

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

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()


    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
    }


    private fun isValidPassword(password: String): Boolean {
        if (password == "123") {
            _txtValidationPassCorrect.value = ""
            return true

        } else {
            _txtValidationPassCorrect.value = _passwordErrors[1]
            return true
        }


    }

    private fun isValidEmail(email: String): Boolean {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            if (email == "lucia.rivera@unmsm.edu.pe") {
                _txtValidationUserCorrect.value = ""
                return true
            } else {
                _txtValidationUserCorrect.value = _userErrors[2]
            }
        } else {
            _txtValidationUserCorrect.value = _userErrors[1]
        }
        return true
    }

    fun onLoginSelected(): Boolean {
        val email = _email.value
        val password = _password.value

        if (email != null && password != null) {
            _isValidationUserCorrect.value = isValidEmail(email)
            _isValidationPassCorrect.value = isValidPassword(password)
            return _isValidationUserCorrect.value == true && _isValidationPassCorrect.value == true
        } else {
            if (email == null)
                _txtValidationUserCorrect.value = _userErrors[0]
            if (password == null)
                _txtValidationPassCorrect.value = _passwordErrors[0]
            _isValidationUserCorrect.value = false
            _isValidationPassCorrect.value = false
            return false
        }
    }

    private fun loadUserData(): Unit {
        val email = _email.value
        val password = _password.value
        if (email != null && password != null) {
            _currentUser.value = repo.getUser(email, password)
        }

    }

    //--
    private fun loadUserDataFromGoogle(user: UserFromGmail): Unit {
        _currentUser.value = repo.getUserFromGoogle(user)

    }

    fun saveLocallyUserData(): Unit {
        loadUserData()
        val curr = _currentUser.value
        if (curr != null) {
            repo.setActiveSession(curr.idUser, true)
            userManager.saveUser(curr)
        }
    }

    fun saveLocallyUserDataFromGoogle(user: UserFromGmail): Unit {
        if (user.email != null) {
            loadUserDataFromGoogle(user)

            val curr = _currentUser.value
            if (curr != null) {
                userManager.saveUser(curr)
            }
        }
    }

    fun getMainRoute(): String {
        val user = _currentUser.value
        var route = ItemMenu.HomeScreen.routeStudent
        if (user != null) {
            val rol = user.idRol

            route = when (rol) {
                1 -> {
                    AppScreens.MainStudentScreen.route
                }

                2 -> {
                    AppScreens.MainDeleScreen.route
                }

                else -> {
                    AppScreens.MainTeacherScreen.route
                }
            }
        }
        return route

    }


    fun onSignWithGoogleResult(result: SignInResult) {
        _state.update {
            it.copy(
                isSignInSuccessful = result.data != null,
                signInError = result.errorMessage
            )
        }
    }

    fun resetState() {
        _state.update { SignInState() }
    }




}