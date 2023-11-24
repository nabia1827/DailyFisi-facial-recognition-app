package com.pruebita.mydailyfisiapp.viewmodel

import android.content.Context
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pruebita.mydailyfisiapp.data.model.helpers.UserManager
import com.pruebita.mydailyfisiapp.data.model.domain.SignInResult
import com.pruebita.mydailyfisiapp.data.model.domain.SignInState
import com.pruebita.mydailyfisiapp.data.model.domain.User
import com.pruebita.mydailyfisiapp.data.model.domain.UserFromGmail
import com.pruebita.mydailyfisiapp.data.model.helpers.TokenManager
import com.pruebita.mydailyfisiapp.data.repository.interfaces.ApiService
import com.pruebita.mydailyfisiapp.data.repository.interfaces.AuthService
import com.pruebita.mydailyfisiapp.data.repository.repositories.AuthRepository

import com.pruebita.mydailyfisiapp.data.repository.repositories.UserRepositoryImpl
import com.pruebita.mydailyfisiapp.data.source.credentials
import com.pruebita.mydailyfisiapp.ui.navigation.AppScreens
import com.pruebita.mydailyfisiapp.ui.navigation.ItemMenu
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject constructor(private val context: Context, private val authService: AuthService, private val apiService: ApiService) : ViewModel() {
    private val repo: UserRepositoryImpl = UserRepositoryImpl(apiService)
    private val authRepository: AuthRepository = AuthRepository(authService)

    private val userManager: UserManager = UserManager(context)
    private val tokenManager: TokenManager = TokenManager(context)

    private val _currentUser = MutableLiveData<User>()
    val currentUser: LiveData<User> = _currentUser

    private val _userErrors = listOf("Campo vacio", "Correo no valido", "Dominio no valido")
    private val _passwordErrors = listOf("Campo vacio", "Contraseña invalida")

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

    init {
        auth()
        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                auth()
            }
        }, 0, 30 * 60 * 1000)

    }


    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
    }


    private fun isValidPassword(password: String): Boolean {
        if (password.length > 4) {
            _txtValidationPassCorrect.value = ""
            return true

        } else {
            _txtValidationPassCorrect.value = _passwordErrors[1]
            return false
        }


    }

    private fun isValidEmail(email: String): Boolean {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            if (validateDomain(email)) {
                _txtValidationUserCorrect.value = ""
                return true
            } else {
                _txtValidationUserCorrect.value = _userErrors[2]
            }
        } else {
            _txtValidationUserCorrect.value = _userErrors[1]
        }
        return false
    }

    suspend fun onLoginSelected(): Boolean {
        val email = _email.value
        val password = _password.value

        if (email != null && password != null) {
            _isValidationUserCorrect.value = isValidEmail(email)
            _isValidationPassCorrect.value = isValidPassword(password)
            var isValid = false
            if(_isValidationUserCorrect.value == true && _isValidationPassCorrect.value == true){
                isValid = repo.validateUser(tokenManager.getToken(),email,password) == 1

                return isValid
            }

            return false
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

    private suspend fun loadUserData(): Unit {
        val email = _email.value
        val password = _password.value
        if (email != null && password != null) {
            _currentUser.value = repo.getUser(tokenManager.getToken(),email, password)
        }

    }

    //--
    private suspend fun loadUserDataFromGoogle(user: UserFromGmail): Unit {
        _currentUser.value = repo.getUserFromGoogle(tokenManager.getToken(),user)


    }

    suspend fun saveLocallyUserData(): Unit {
        loadUserData()
        val curr = _currentUser.value
        if (curr != null) {
            repo.setActiveSession(tokenManager.getToken(),curr.idUser, true)
            userManager.saveUser(curr)

        }
    }

    suspend fun saveLocallyUserDataFromGoogle(user: UserFromGmail): Unit {
        if (user.email != null) {
            loadUserDataFromGoogle(user)

            val curr = _currentUser.value
            if (curr != null) {
                repo.setActiveSession(tokenManager.getToken(),curr.idUser, true)
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

    fun auth() {
        viewModelScope.launch {
            try {

                val token = authRepository.auth(credentials).Token
                tokenManager.saveToken(token)
            } catch (e: Exception) {
                println("error en login: ${e.message}")

            }
        }
    }

    private fun validateDomain(email: String): Boolean {
        val domain = "@unmsm.edu.pe"
        val regex = Regex(".*$domain$")

        return regex.matches(email)
    }

    suspend fun getUserActive():Boolean{
        val first = !repo.getUserActive(tokenManager.getToken(),userManager.getIdUser())
        _isFirstLogin.postValue(first)
        return first
    }


    fun setUserActive(userActive: Boolean){
        viewModelScope.launch{
            _isFirstLogin.postValue(!userActive)
            repo.setUserActive(tokenManager.getToken(),userManager.getIdUser(), userActive)
        }
    }




}