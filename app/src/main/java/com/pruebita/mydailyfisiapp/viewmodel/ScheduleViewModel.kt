package com.pruebita.mydailyfisiapp.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pruebita.mydailyfisiapp.data.model.domain.Course
import com.pruebita.mydailyfisiapp.data.model.domain.Reminder
import com.pruebita.mydailyfisiapp.data.model.helpers.AcademicTimeManager
import com.pruebita.mydailyfisiapp.data.model.helpers.TokenManager
import com.pruebita.mydailyfisiapp.data.model.helpers.UserManager
import com.pruebita.mydailyfisiapp.data.repository.interfaces.ApiService
import com.pruebita.mydailyfisiapp.data.repository.repositories.CourseRepositoryImpl
import com.pruebita.mydailyfisiapp.data.repository.repositories.ReminderRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.todayIn
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone
import java.util.Timer
import java.util.TimerTask
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(private val context: Context,private val apiService: ApiService): ViewModel(){
    private val repoCourse: CourseRepositoryImpl = CourseRepositoryImpl(apiService)
    private val remiCalendar: ReminderRepositoryImpl =  ReminderRepositoryImpl()
    private val userManager: UserManager = UserManager(context)
    private val tokenManager: TokenManager = TokenManager(context)
    private val academicTimeManager: AcademicTimeManager = AcademicTimeManager(context)

    private val _currentIdUser = MutableLiveData<Int>(userManager.getIdUser())
    val currentIdUser: LiveData<Int> = _currentIdUser

    private val _currentWeek = MutableLiveData<Int>(academicTimeManager.getCurrentWeek())
    val currentWeek: LiveData<Int> = _currentWeek

    private val _courses = MutableLiveData<MutableList<Course>>()
    val courses: LiveData<MutableList<Course>> = _courses

    private val _reminders = MutableLiveData<MutableList<Reminder>>()
    val reminders: LiveData<MutableList<Reminder>> = _reminders

    private val _currentIndex = MutableLiveData<Int>()
    val currentIndex: LiveData<Int> = _currentIndex

    private val _actualClassStartTime = MutableLiveData<Calendar>()
    val classStartTime: LiveData<Calendar> = _actualClassStartTime

    private val _indexTemp = MutableLiveData<Int>()
    val indexTemp: LiveData<Int> = _indexTemp

    private val timeZone = TimeZone.getTimeZone("America/Lima")

    private val _isReminderRegister = MutableLiveData<Boolean>()
    val isReminderRegister: LiveData<Boolean> =_isReminderRegister

    init {

        val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        dateFormat.timeZone = timeZone
        val timer = Timer()

        val today = Clock.System.todayIn(kotlinx.datetime.TimeZone.currentSystemDefault())

        getCourseInfo(today)
        _reminders.postValue(getReminderInfo(today))

        _indexTemp.postValue(findCurrentIndex(courses.value))


        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                //println("aaaaaaaa")
                val indexTemp = indexTemp.value
                if (indexTemp!=-1 && indexTemp!=null){
                    val currentTime = Calendar.getInstance(timeZone)
                    if(CompareTime(currentTime)){
                        //print("no entroooo pipipi")
                        if(indexTemp + 1 < (courses.value?.size ?: -1)){
                            val tempCourse = _courses.value?.get(indexTemp+1)
                            if (tempCourse != null) {
                                if(isTimeActual(tempCourse)){
                                    _currentIndex.postValue(indexTemp+1)
                                    _indexTemp.postValue(indexTemp+1)
                                    _actualClassStartTime.postValue(tempCourse.endDate)
                                }
                                else{
                                    _currentIndex.postValue(-1)
                                    _actualClassStartTime.postValue(tempCourse.startDate)
                                }
                            }
                        }else{
                            _indexTemp.postValue(-1)
                        }
                    }
                }
                else{
                    _indexTemp.postValue(findCurrentIndex(courses.value))
                }
            }
        }, 0, 1000) // Actualizar cada segundo*/
    }

    private fun CompareTime(currentTime: Calendar): Boolean {
        return (currentTime.get(Calendar.YEAR)         == classStartTime.value?.get(Calendar.YEAR) &&
                currentTime.get(Calendar.MONTH)        == classStartTime.value?.get(Calendar.MONTH) &&
                currentTime.get(Calendar.DAY_OF_MONTH) == classStartTime.value?.get(Calendar.DAY_OF_MONTH) &&
                currentTime.get(Calendar.HOUR_OF_DAY)  == classStartTime.value?.get(Calendar.HOUR_OF_DAY) &&
                currentTime.get(Calendar.MINUTE)       == classStartTime.value?.get(Calendar.MINUTE) )
    }

    fun setCourseInfo(specificDate: LocalDate){
        viewModelScope.launch{
            val newCourses = repoCourse.getCourseInfoFromTime(tokenManager.getToken(),userManager.getIdUser(),specificDate)
            _indexTemp.postValue(-1)
            _courses.postValue(newCourses)
        }

    }

    private fun getCourseInfo(specificDate: LocalDate) {
        viewModelScope.launch{
            _courses.postValue(repoCourse.getCourseInfoFromTime(tokenManager.getToken(),userManager.getIdUser(),specificDate))
        }

    }

    fun setReminderInfo(specificDate: LocalDate){
        val newReminders = remiCalendar.listAllRemindersEvents(specificDate)
        _reminders.postValue(newReminders)
    }

    fun getReminderInfo(specificDate: LocalDate): MutableList<Reminder> {
        return remiCalendar.listAllRemindersEvents(specificDate)
    }

    fun getReminder(idReminder:Int): Reminder? {
        val reminder = _reminders.value?.find {
            it.idReminder == idReminder
        }
        return reminder
    }

    fun getCourse(idCourse:Int): Course? {
        val course = _courses.value?.find {
            it.idCourse == idCourse
        }
        return course
    }

    fun addReminder(
        title: String,
        endHour: Calendar,
        initHour: Calendar,
        selectedDate: Calendar
    ) {
        val startTime = LocalDateTime(
            selectedDate.get(Calendar.YEAR),
            selectedDate.get(Calendar.MONTH),
            selectedDate.get(Calendar.DAY_OF_MONTH),
            initHour.get(Calendar.HOUR_OF_DAY),
            initHour.get(Calendar.MINUTE)
        )

        val endTime = LocalDateTime(
            selectedDate.get(Calendar.YEAR),
            selectedDate.get(Calendar.MONTH),
            selectedDate.get(Calendar.DAY_OF_MONTH),
            endHour.get(Calendar.HOUR_OF_DAY),
            endHour.get(Calendar.MINUTE)
        )

        val newReminderid = remiCalendar.addReminder(
            currentIdUser.value,
            title,
            startTime,
            endTime
        )

        val newReminder = Reminder(
            idReminder = newReminderid,
            title = title,
            dateStart = startTime,
            dateEnd = endTime,
            isDone = false
        )
        _reminders.value?.add(newReminder)
    }


    fun markAsCompleted(idReminder: Int){
        val reminderToUpdate = _reminders.value?.find {
            it.idReminder == idReminder
        }
        remiCalendar.markAsCompleted(idReminder, currentIdUser.value)

        reminderToUpdate?.let {
            it.isDone = true
        }
    }



    fun editReminder(
        idReminder: Int,
        title: String,
        endHour: Calendar,
        initHour: Calendar,
        selectedDate: Calendar
    ) {

        val startTime = LocalDateTime(
            selectedDate.get(Calendar.YEAR),
            selectedDate.get(Calendar.MONTH),
            selectedDate.get(Calendar.DAY_OF_MONTH),
            initHour.get(Calendar.HOUR_OF_DAY),
            initHour.get(Calendar.MINUTE)
        )

        val endTime = LocalDateTime(
            selectedDate.get(Calendar.YEAR),
            selectedDate.get(Calendar.MONTH),
            selectedDate.get(Calendar.DAY_OF_MONTH),
            endHour.get(Calendar.HOUR_OF_DAY),
            endHour.get(Calendar.MINUTE)
        )

        remiCalendar.editReminder(
            currentIdUser.value,
            idReminder,
            title,
            startTime,
            endTime
        )

        val reminderToUpdate = _reminders.value?.find {
            it.idReminder == idReminder
        }

        reminderToUpdate?.let {
            it.title = title
            it.dateStart = startTime
            it.dateEnd = endTime
        }

    }

    private  fun findCurrentIndex(courses: MutableList<Course>?): Int {
        val n = courses?.size
        if ((n != null) && (n > 0)){
            repeat(n){ index->
                val courseTarget = courses.get(index)
                val isActual = isTimeActual(courseTarget)
                if (isActual){
                    _currentIndex.postValue(index)
                    println("ahhhhh estoyyy: $index")
                    return index
                }
            }
        }
        _currentIndex.postValue(-1)
        return -1
    }

    @SuppressLint("SimpleDateFormat")
    private  fun isTimeActual(course: Course):Boolean {
        val timeZone = TimeZone.getTimeZone("America/Lima")
        val currentTime = Calendar.getInstance(timeZone)
        val inicio = course.startDate
        val final = course.endDate

        if (inicio!=null && final!=null){

            val isActualTime = currentTime.after(inicio) && currentTime.before(final)
            println("que devuelve?= $isActualTime")
            _actualClassStartTime.postValue(course.endDate)
            return isActualTime
        }

        return false
    }





}