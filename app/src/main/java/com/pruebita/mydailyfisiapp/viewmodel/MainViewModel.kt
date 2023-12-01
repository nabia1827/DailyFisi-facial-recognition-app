package com.pruebita.mydailyfisiapp.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pruebita.mydailyfisiapp.data.model.domain.User
import com.pruebita.mydailyfisiapp.data.model.helpers.AcademicTimeManager
import com.pruebita.mydailyfisiapp.data.model.helpers.TokenManager
import com.pruebita.mydailyfisiapp.data.model.helpers.UserManager
import com.pruebita.mydailyfisiapp.data.repository.interfaces.ApiService
import com.pruebita.mydailyfisiapp.data.repository.repositories.AttendanceRepositoryImpl
import com.pruebita.mydailyfisiapp.data.repository.repositories.RolRepositoryImpl
import com.pruebita.mydailyfisiapp.data.repository.repositories.SemesterRepositoryImpl
import com.pruebita.mydailyfisiapp.data.repository.repositories.StorageImagesImpl
import com.pruebita.mydailyfisiapp.data.repository.repositories.UserRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone
import java.util.Timer
import java.util.TimerTask
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val context: Context, private val apiService: ApiService) : ViewModel() {

    private val userManager: UserManager = UserManager(context)
    private val tokenManager: TokenManager = TokenManager(context)

    private val academicTimeManager: AcademicTimeManager = AcademicTimeManager(context)

    private val _currentUser = MutableLiveData<User>(userManager.getUser() ?: User())
    val currentUser: LiveData<User> = _currentUser

    private val _currentUserRol = MutableLiveData<String>()
    val currentUserRol: LiveData<String> = _currentUserRol

    private val repo: UserRepositoryImpl = UserRepositoryImpl(apiService)
    private val repoRol: RolRepositoryImpl = RolRepositoryImpl()
    private val storage: StorageImagesImpl = StorageImagesImpl()
    private val repoSemester: SemesterRepositoryImpl = SemesterRepositoryImpl()

    val timeZone: TimeZone = TimeZone.getTimeZone("America/Lima")


    init{
        val week = repoSemester.getCurrentWeek()
        academicTimeManager.saveCurrentWeek(week)

        val semester = repoSemester.getCurrentSemester()
        academicTimeManager.saveCurrentSemester(semester)

        val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        dateFormat.timeZone = timeZone
        val timer = Timer()
        val updateSemester = object : TimerTask() {
            override fun run() {
                val semester = repoSemester.getCurrentSemester()
                academicTimeManager.saveCurrentSemester(semester)

            }
        }
        val start = Calendar.getInstance(timeZone)
        val timeToMidnight:Long = ((24 - start.get(Calendar.HOUR_OF_DAY)) * 60 * 60 * 1000).toLong()
        timer.scheduleAtFixedRate(updateSemester, timeToMidnight, 24 * 60 * 60 * 1000) //each 24 hours

        //Update week
        val updateWeek = object : TimerTask() {
            override fun run() {
                val week = repoSemester.getCurrentWeek()
                academicTimeManager.saveCurrentWeek(week)

            }
        }

        val timeToSunday= (Calendar.SUNDAY - start.get(Calendar.DAY_OF_WEEK) + 7) % 7
        start.add(Calendar.DAY_OF_YEAR, timeToSunday)
        start.set(Calendar.HOUR_OF_DAY, 0)
        start.set(Calendar.MINUTE, 0)
        start.set(Calendar.SECOND, 0)
        timer.scheduleAtFixedRate(updateWeek, start.timeInMillis , 7 * 24 * 60 * 60 * 1000) // Cada 7 días

    }

    fun getUserFullName(): String {
        val user = _currentUser.value
        if (user != null) {
            return user.names + " " + user.firstLastName
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

    fun setUserImageUri(uri: Uri) {
        val user = _currentUser.value
        val urlString = ""
        if (user != null) {
            val nameImage = "user_" + user.idUser
            user.imageUser = storage.uploadImageToStorage(
                uri,
                nameImage,
                "profiles",
                "users"
            )
            _currentUser.value = user
            repo.updateImageUser(tokenManager.getToken(),user)

        }
    }


    fun logOut() {
        var tmp = _currentUser.value
        if (tmp != null) {
            repo.setActiveSession(tokenManager.getToken(),tmp.idUser, false)

        }
    }
}