package com.pruebita.mydailyfisiapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pruebita.mydailyfisiapp.data.model.domain.Event
import com.pruebita.mydailyfisiapp.data.model.domain.User
import com.pruebita.mydailyfisiapp.data.model.helpers.UserManager
import com.pruebita.mydailyfisiapp.data.repository.repositories.EventRepositoryImpl
import com.pruebita.mydailyfisiapp.data.repository.repositories.RolRepositoryImpl
import com.pruebita.mydailyfisiapp.data.repository.repositories.UserRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val context: Context): ViewModel(){
    private val userManager: UserManager = UserManager(context)

    private val repo: UserRepositoryImpl = UserRepositoryImpl()
    private val repoRol: RolRepositoryImpl = RolRepositoryImpl()
    private val repoEvent: EventRepositoryImpl = EventRepositoryImpl()

    private val _currentUser = MutableLiveData<User>(userManager.getUser() ?: User())
    val currentUser: LiveData<User> = _currentUser

    private val _currentUserRol = MutableLiveData<String>()
    val currentUserRol: LiveData<String> = _currentUserRol

    private val _todayEvents = MutableLiveData<MutableList<Event>>(repoEvent.listAllTodayEvents())
    val todayEvents: LiveData<MutableList<Event>> = _todayEvents

    fun refreshCurrentUser() {
        _currentUser.value = userManager.getUser() ?: User()
        val tmp = _currentUser.value
        if (tmp != null) {
            _currentUserRol.value = repoRol.getRol(tmp.idRol)
        }
    }

    fun getHourAndMinutes(calendar: Calendar): String{
        val hours = calendar.get(Calendar.HOUR_OF_DAY) // Obtiene las horas en formato de 24 horas
        val minutes = calendar.get(Calendar.MINUTE)
        return String.format("%02d:%02d", hours, minutes)
    }


}