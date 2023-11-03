package com.pruebita.mydailyfisiapp.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pruebita.mydailyfisiapp.data.model.User
import com.pruebita.mydailyfisiapp.data.model.UserManager
import com.pruebita.mydailyfisiapp.data.repository.repositories.RolRepositoryImpl
import com.pruebita.mydailyfisiapp.data.repository.repositories.UserRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val context: Context): ViewModel(){

    private val userManager: UserManager = UserManager(context)

    private val _currentUser = MutableLiveData<User>(userManager.getUser() ?: User())
    val currentUser: LiveData<User> = _currentUser

    private val _currentUserRol = MutableLiveData<String>()
    val currentUserRol : LiveData<String> = _currentUserRol

    private val repo: UserRepositoryImpl = UserRepositoryImpl()
    private val repoRol: RolRepositoryImpl = RolRepositoryImpl()

    fun getUserFullName():String{
        val user = _currentUser.value
        if(user != null){
            return user.names + " " +user.firstLastName
        }
        return "desconocido"
    }


    fun refreshCurrentUser() {
        _currentUser.value = userManager.getUser() ?: User()
        val tmp = _currentUser.value
        if (tmp != null) {
            _currentUserRol.value = repoRol.getRol(tmp.idRol)
        }
    }
    fun setUserImageUri(uri: Uri){

    }

    fun setUserImage(img: String){
        var tmp = _currentUser.value
        if(tmp != null ){
            tmp.imageUser = img
            _currentUser.value = tmp
            repo.updateUser(tmp)
        }
    }

    fun logOut(){
        var tmp = _currentUser.value
        if (tmp != null) {
            repo.setActiveSession(tmp.idUser,false)
        }
    }
}