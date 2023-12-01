package com.pruebita.mydailyfisiapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pruebita.mydailyfisiapp.data.model.domain.StudentAssistUnit
import com.pruebita.mydailyfisiapp.data.model.helpers.AcademicTimeManager
import com.pruebita.mydailyfisiapp.data.model.helpers.TokenManager
import com.pruebita.mydailyfisiapp.data.model.helpers.UserManager
import com.pruebita.mydailyfisiapp.data.repository.interfaces.ApiService
import com.pruebita.mydailyfisiapp.data.repository.repositories.AttendanceRepositoryImpl
import com.pruebita.mydailyfisiapp.data.repository.repositories.CourseRepositoryImpl
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
class CurseReportTeacherViewModel
@Inject constructor(private val context: Context,private val apiService: ApiService): ViewModel(){
    private val repoCourse: CourseRepositoryImpl = CourseRepositoryImpl(apiService)
    private val repoAssist: AttendanceRepositoryImpl = AttendanceRepositoryImpl()
    private val academicTimeManager: AcademicTimeManager = AcademicTimeManager(context)
    private val userManager: UserManager = UserManager(context)
    private val tokenManager: TokenManager = TokenManager(context)

    private val _idCourse = MutableLiveData<Int>(null)
    val idCourse: LiveData<Int> = _idCourse

    private val _courseName = MutableLiveData<String>("")
    val courseName: LiveData<String> = _courseName

    private val _section = MutableLiveData<Int>(0)
    val section: LiveData<Int> = _section

    private val _totalClasses = MutableLiveData<Int>(0)
    val totalClasses: LiveData<Int> = _totalClasses

    private val _cont = MutableLiveData<Int>(0)
    val cont: LiveData<Int> = _cont

    private val _startTime = MutableLiveData<Calendar>()
    val startTime: LiveData<Calendar> = _startTime

    private val _endTime = MutableLiveData<Calendar>()
    val endTime: LiveData<Calendar> = _endTime

    private val _isToday = MutableLiveData<Boolean>()
    val isToday: LiveData<Boolean> = _isToday

    private val _listStudents = MutableLiveData<MutableList<StudentAssistUnit>>(mutableListOf())
    val listStudents: LiveData<MutableList<StudentAssistUnit>> = _listStudents

    private val _listTheoryAssists = MutableLiveData<MutableList<Int>>(mutableListOf())
    val listTheoryAssists: LiveData<MutableList<Int>> = _listTheoryAssists

    private val _listLabAssists = MutableLiveData<MutableList<Int>>(mutableListOf())
    val listLabAssists : LiveData<MutableList<Int>> = _listLabAssists

    val timeZone: TimeZone = TimeZone.getTimeZone("America/Lima")
    init{
        _idCourse.value =-1
        _startTime.value = null
        _endTime.value = null
        val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        dateFormat.timeZone = timeZone
        val timer = Timer()

        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val now = Calendar.getInstance(timeZone)
                val id = _idCourse.value
                if(id != null){
                    _isToday.postValue(repoCourse.isToday(tokenManager.getToken(),id,userManager.getIdUser()))

                    if(_isToday.value ==true){
                        val start = _startTime.value
                        val end = _endTime.value
                        if(start != null && end !=null){
                            print(isCurrent(start,now, end))
                            if(isCurrent(start,now, end)) {
                                val theoAssists = repoAssist.getSectionTheoryReport(id)
                                val labAssists = repoAssist.getSectionLabReport(id)
                                if(theoAssists != null){//Only theory part?
                                    _listTheoryAssists.postValue(theoAssists)
                                    _listLabAssists.postValue(labAssists)
                                    val cn = _cont.value
                                    if(cn != null){
                                        if(cn <500){
                                            _cont.postValue(cn +1)
                                        }
                                        else{
                                            _cont.postValue(0)
                                        }
                                        println("hey no null cont: ${cont.value}")
                                    }else{
                                        println("hey: ${cont.value}")
                                    }

                                }else{
                                    println("theo null")
                                }


                            }
                            else{
                                println("no current")
                            }
                        }

                    }
                    else{
                        println("is today?")
                    }
                }
                else{
                    println("id coursejhvbfddss $id")
                }

            }
        }, 0, 5000)

    }

    fun updateTeacherReport(idCourse:Int){
        _totalClasses.value = academicTimeManager.getCurrentWeek()
        _idCourse.value = idCourse
        val course = repoCourse.getCourseSummary(tokenManager.getToken(),idCourse, userManager.getIdUser())
        _courseName.value = course?.courseName
        _section.value = course?.section
        _endTime.value = course?.endDate
        _startTime.value = course?.startDate
        _cont.value =0

        val list = repoAssist.getStudentList(idCourse)
        val theoAssists = repoAssist.getSectionTheoryReport(idCourse)
        val labAssists = repoAssist.getSectionLabReport(idCourse)

        if(list != null && list.isNotEmpty()){
            _listStudents.value =list
            _listTheoryAssists.value = theoAssists
            _listLabAssists.value = labAssists

        }else{
            _listStudents.value = null
            _listTheoryAssists.value = null
            _listLabAssists.value = null


        }

    }
    fun isCurrent(courseStart:Calendar,now:Calendar, courseEnd:Calendar):Boolean{
        return now.timeInMillis>courseStart.timeInMillis && now.timeInMillis<courseEnd.timeInMillis
    }



}