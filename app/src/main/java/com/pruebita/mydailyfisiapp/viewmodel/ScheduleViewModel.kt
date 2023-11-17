package com.pruebita.mydailyfisiapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pruebita.mydailyfisiapp.data.model.domain.Course
import com.pruebita.mydailyfisiapp.data.model.domain.Reminder
import com.pruebita.mydailyfisiapp.data.repository.repositories.CourseRepositoryImpl
import com.pruebita.mydailyfisiapp.data.repository.repositories.ReminderRepositoryImpl
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.todayIn
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone
import java.util.Timer
import java.util.TimerTask

class ScheduleViewModel (): ViewModel(){
    private val repoCourse: CourseRepositoryImpl = CourseRepositoryImpl()
    private val remiCalendar: ReminderRepositoryImpl =  ReminderRepositoryImpl()

    private val _courses = MutableLiveData<MutableList<Course>>()
    val courses: LiveData<MutableList<Course>> = _courses

    private val _reminders = MutableLiveData<MutableList<Reminder>>()
    val reminders: LiveData<MutableList<Reminder>> = _reminders

    private val _currentIndex = MutableLiveData<Int>()
    val currentIndex: LiveData<Int> = _currentIndex

    private val _actualClassStartTime = MutableLiveData<Calendar>()
    val classStartTime: LiveData<Calendar> = _actualClassStartTime

    private val _actualClassEndTime = MutableLiveData<Calendar>()
    val classEndTime: LiveData<Calendar> = _actualClassEndTime

    init {
        val timeZone = TimeZone.getTimeZone("America/Lima")
        val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        dateFormat.timeZone = timeZone
        val timer = Timer()

        val courses = _courses.value
        var isFind = findCurrentIndex(courses)

        timer.scheduleAtFixedRate(object : TimerTask() {
            val today = Clock.System.todayIn(kotlinx.datetime.TimeZone.currentSystemDefault())
            override fun run() {
                if (isFind){

                }
            }
        }, 0, 1000) // Actualizar cada segundo*/
    }

    fun setCourseInfo(specificDate: LocalDate){
        val newCourses = repoCourse.getCourseInfoFromTime(specificDate)
        _courses.postValue(newCourses)
    }

    fun getCourseInfo(specificDate: LocalDate): MutableList<Course> {
        return repoCourse.getCourseInfoFromTime(specificDate)
    }

    fun setReminderInfo(specificDate: LocalDate){
        val newReminders = remiCalendar.listAllRemindersEvents(specificDate)
        _reminders.postValue(newReminders)
    }

    fun getReminderInfo(specificDate: LocalDate): MutableList<Reminder> {
        return remiCalendar.listAllRemindersEvents(specificDate)
    }

    fun findCurrentIndex(courses: MutableList<Course>?):Boolean{
        val n = courses?.size
        if ((n != null) && (n > 0)){
            repeat(n){ index->
                val couseTarget = courses.get(index)
                val isActual = isTimeActual(couseTarget)
                if (isActual){
                    _currentIndex.postValue(index)

                    return true
                }
            }
        }
        _currentIndex.postValue(-1)

        return false
    }

    fun isTimeActual(course: Course):Boolean {
        val currentTime = Calendar.getInstance()
        val inicio = course.startDate
        val final = course.endDate
        val isActual = currentTime.after(inicio) && currentTime.before(final)
        return isActual
    }

}