package com.pruebita.mydailyfisiapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pruebita.mydailyfisiapp.data.model.domain.Course
import com.pruebita.mydailyfisiapp.data.model.domain.Event
import com.pruebita.mydailyfisiapp.data.model.domain.User
import com.pruebita.mydailyfisiapp.data.model.helpers.TokenManager
import com.pruebita.mydailyfisiapp.data.model.helpers.UserManager
import com.pruebita.mydailyfisiapp.data.repository.interfaces.ApiService
import com.pruebita.mydailyfisiapp.data.repository.repositories.CourseRepositoryImpl
import com.pruebita.mydailyfisiapp.data.repository.repositories.EventRepositoryImpl
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
class ClockViewModel @Inject constructor(private val context: Context,private val apiService: ApiService): ViewModel() {
    private val repoCourse: CourseRepositoryImpl = CourseRepositoryImpl()
    private val repoEvent: EventRepositoryImpl = EventRepositoryImpl(apiService)
    private val userManager: UserManager = UserManager(context)
    private val tokenManager: TokenManager = TokenManager(context)
    private var currentIndex = -1

    private var todayCourses: MutableList<Course> = mutableListOf()
    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    private val _todayEvents = MutableLiveData<MutableList<Event>>()
    val todayEvents: LiveData<MutableList<Event>> = _todayEvents

    private val _courses = MutableLiveData<MutableList<Course>>()
    val courses: LiveData<MutableList<Course>> = _courses

    private val _currentTimeText = MutableLiveData<String>()
    val currentTimeText: LiveData<String> = _currentTimeText

    private val _currentTime = MutableLiveData<Calendar>()
    val currentTime: LiveData<Calendar> = _currentTime

    private val _classStartTime = MutableLiveData<Calendar>()
    val classStartTime: LiveData<Calendar> = _classStartTime

    private val _classEndTime = MutableLiveData<Calendar>()
    val classEndTime: LiveData<Calendar> = _classEndTime

    private val _timeRemaining = MutableLiveData<String>()
    val timeRemaining: LiveData<String> = _timeRemaining

    private val _isFinished = MutableLiveData<Boolean>(true)
    val isFinished: LiveData<Boolean> = _isFinished

    private val _pendingCourses = MutableLiveData<Int>(0)
    val pendingCourses: LiveData<Int> = _pendingCourses

    // Courses
    private val _actualCourse = MutableLiveData<Course>(null)
    val actualCourse: LiveData<Course> = _actualCourse

    private val _nextCourse = MutableLiveData<Course>(null)
    val nextCourse: LiveData<Course> = _nextCourse

    private val _subNextCourse = MutableLiveData<Course>(null)
    val subNextCourse: LiveData<Course> = _subNextCourse


    init {
        realizarSolicitud()
        _user.value = userManager.getUser() ?: User()
        val user = _user.value

        if(user != null ){
            todayCourses = repoCourse.getTodayCourses(user.idUser)
            _courses.value = repoCourse.getUserCourses(user.idUser)

            println(user.idUser)
            if(todayCourses != null){
                if(todayCourses.size != 0){
                    currentIndex = 0
                    _isFinished.postValue(false)
                    _classStartTime.value = todayCourses[currentIndex].theoryPart.startHour
                    _classEndTime.value = todayCourses[currentIndex].labPart.endHour
                    _actualCourse.value = todayCourses[currentIndex]
                    _pendingCourses.value = todayCourses.size

                    if((currentIndex +1) < todayCourses.size){
                        _nextCourse.value = todayCourses[currentIndex +1]

                        if((currentIndex +2) < todayCourses.size){
                            _subNextCourse.value = todayCourses[currentIndex + 2]
                        }
                    }

                }
            }
        }

        val timeZone = TimeZone.getTimeZone("America/Lima")
        val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        dateFormat.timeZone = timeZone
        val timer = Timer()
        val now = Calendar.getInstance(timeZone)

        val timeToMidnight:Long = ((24 - now.get(Calendar.HOUR_OF_DAY)) * 60 * 60 * 1000).toLong()

        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                realizarSolicitud()
                val user = _user.value
                if (user!=null){
                    _courses.value = repoCourse.getUserCourses(user.idUser)
                    val newCourses = repoCourse.getTodayCourses(user.idUser)

                    todayCourses.clear()
                    todayCourses.addAll(newCourses)

                    if(todayCourses != null){
                        if(todayCourses.size != 0){
                            currentIndex = 0
                            _isFinished.postValue(false)
                            _classStartTime.postValue(todayCourses[currentIndex].theoryPart.startHour)
                            _classEndTime.postValue(todayCourses[currentIndex].labPart.endHour)
                            _actualCourse.postValue(todayCourses[currentIndex])
                            _pendingCourses.postValue(todayCourses.size)

                            if((currentIndex +1) < todayCourses.size){
                                _nextCourse.postValue(todayCourses[currentIndex +1])

                                if((currentIndex +2) < todayCourses.size){
                                    _subNextCourse.postValue(todayCourses[currentIndex + 2])
                                }
                            }

                        }
                    }
                }
            }
        }, timeToMidnight, 24 * 60 * 60 * 1000)


        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val calendar = Calendar.getInstance(timeZone)
                val time = dateFormat.format(calendar.time)
                _currentTime.postValue(calendar)
                _currentTimeText.postValue(time)
                if(_isFinished.value != true){
                    refreshTimeRemaining()
                }

            }
        }, 0, 1000) // Actualizar cada segundo
    }

    fun refreshTimeRemaining(){
        val classTime = _classStartTime.value
        val classEndTime = _classEndTime.value
        val actualTime = _currentTime.value

        if(classTime !=null && actualTime!=null && classEndTime != null){
            val remaining = classTime.timeInMillis - actualTime.timeInMillis
            val seconds:Int = ((remaining / 1000) % 60).toInt()
            val minutes:Int = ((remaining / (1000 * 60)) % 60).toInt()
            val hours:Int = ((remaining / (1000 * 60 * 60)) % 24).toInt()

            if(seconds <= 0 && minutes<=0 && hours <= 0){
                val remaining2 = classEndTime.timeInMillis - actualTime.timeInMillis
                val tenMinutes: Long = 1 * 60 * 1000 // 10 minutos en milisegundos

                if(remaining2 <= tenMinutes){ //Change to 10 minutes
                    _timeRemaining.postValue("")
                    moveToNextCurse()
                }else{
                    _timeRemaining.postValue("En curso")
                }

            }else{
                if(hours == 0){
                    if(minutes == 0){
                        _timeRemaining.postValue("Faltan $seconds segundo(s)")
                    }else{
                        _timeRemaining.postValue("Faltan $minutes minuto(s)")
                    }

                }else{
                    _timeRemaining.postValue("Faltan $hours hr(s)")
                }
            }

        }

    }

    private fun moveToNextCurse(){

        if(todayCourses != null && (currentIndex + 1) < todayCourses.size){

            currentIndex += 1
            _classStartTime.postValue(todayCourses[currentIndex].theoryPart.startHour)
            _classEndTime.postValue(todayCourses[currentIndex].labPart.endHour)
            _actualCourse.postValue(todayCourses[currentIndex])
            _pendingCourses.postValue(todayCourses.size -currentIndex)

            if((currentIndex + 1) < todayCourses.size){
                _nextCourse.postValue(todayCourses[currentIndex + 1 ])

                if((currentIndex + 2) < todayCourses.size){
                    _subNextCourse.postValue(todayCourses[currentIndex + 2 ])
                }else{
                    _subNextCourse.postValue(null)
                }

            }else{
                _nextCourse.postValue(null)
            }

        }else{
            _actualCourse.postValue(null)
            _isFinished.postValue(true)
            _pendingCourses.postValue(0)
        }
    }

    fun formatHour(calendar: Calendar): String {
        val sdf = SimpleDateFormat("h:mma", Locale.getDefault())
        val timeZone = TimeZone.getTimeZone("America/Lima")
        sdf.timeZone = timeZone
        return sdf.format(calendar.time)
    }

    fun realizarSolicitud() {
        viewModelScope.launch {
            _todayEvents.postValue(repoEvent.listAllTodayEvents(tokenManager.getToken()))


        }
    }


}