package com.pruebita.mydailyfisiapp.data.repository.repositories

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.JsonPrimitive
import com.pruebita.mydailyfisiapp.data.model.domain.Course
import com.pruebita.mydailyfisiapp.data.model.domain.CourseSummary
import com.pruebita.mydailyfisiapp.data.model.domain.SubPartSummary
import com.pruebita.mydailyfisiapp.data.repository.interfaces.ApiService
import com.pruebita.mydailyfisiapp.data.repository.interfaces.CourseRepository
import com.pruebita.mydailyfisiapp.data.source.*
import kotlinx.datetime.LocalDate
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

class CourseRepositoryImpl(private val apiService: ApiService): CourseRepository {
    private val timeZone: TimeZone = TimeZone.getTimeZone("America/Lima")
    private val initMin = 17
    private val initHour = 13
    val day = 18

    //with order
    @RequiresApi(Build.VERSION_CODES.O)
    override fun getTodayCourses(token: String, idUser: Int): MutableList<Course>? {
        val authorizationHeader = "Bearer $token"
        val calendar = Calendar.getInstance(timeZone)
        var list : MutableList<Course> = mutableListOf()
        if(idUser != 5){
            when(calendar.get(Calendar.DAY_OF_WEEK)){
                Calendar.MONDAY-> {
                    list.add(sd)
                    list.add(met)
                }
                Calendar.TUESDAY-> {
                    list.add(ingInfo)
                    list.add(si)
                }
                Calendar.WEDNESDAY-> {
                    list.add(gdp)
                }
                Calendar.THURSDAY-> {

                }
                Calendar.FRIDAY-> {
                    list.add(audi)
                }
                Calendar.SATURDAY-> {
                    list.add(moviles)
                    list.add(calculo)
                    list.add(mkt)
                }
                Calendar.SUNDAY-> {

                }
            }
        }else{
            when(calendar.get(Calendar.DAY_OF_WEEK)){
                Calendar.MONDAY-> {

                }
                Calendar.TUESDAY-> {

                }
                Calendar.WEDNESDAY-> {

                }
                Calendar.THURSDAY-> {

                }
                Calendar.FRIDAY-> {

                }
                Calendar.SATURDAY-> {

                    list.add(calculo)
                    list.add(mkt)
                }
                Calendar.SUNDAY-> {

                }
            }
        }

        return list
    }

    // without order
    override fun getUserCourses(token: String, idUser: Int): MutableList<Course>? {
        val authorizationHeader = "Bearer $token"
        return if(idUser != 5){
            mutableListOf(sd, met, ingInfo, si, gdp, audi, moviles, calculo, mkt)
        }else{
            mutableListOf(calculo, mkt)
        }

    }

    override fun getCourseShortInfo(token:String,idCourse: Int,idUser: Int): Course? {
        val authorizationHeader = "Bearer $token"
        var course:Course? = Course()
        when (idCourse) {
            1 -> {
                course = sd
            }
            2 -> {
                course = met
            }
            3 -> {
                course = ingInfo
            }
            4 -> {
                course = si
            }
            5 -> {
                course = audi
            }
            6 -> {
                course = moviles
            }
            7 -> {
                course = moviles
            }
            8 -> {
                course = calculo
            }
            9 -> {
                course = mkt
            }

        }
        return course
    }

    override fun getSubPartSummary(token:String,idCourse: Int, idSubPart: Int,idUser: Int): SubPartSummary? {
        val authorizationHeader = "Bearer $token"
        var course:Course? = Course()
        var summary:SubPartSummary = SubPartSummary()
        when (idCourse) {
            1 -> {
                course = sd
            }
            2 -> {
                course = met
            }
            3 -> {
                course = ingInfo
            }
            4 -> {
                course = si
            }
            5 -> {
                course = audi
            }
            6 -> {
                course = moviles
            }
            7 -> {
                course = moviles
            }
            8 -> {
                course = calculo
            }
            9 -> {
                course = mkt
            }

        }
        summary.idCourse = course?.idCourse ?: 0
        summary.courseName = course?.courseName?:""
        summary.section = course?.section?:0
        if(idSubPart == 1){
            summary.startDate = course?.theoryPart?.startHour?: Calendar.getInstance()
            summary.endDate= course?.theoryPart?.endHour?: Calendar.getInstance()
            summary.isFinished = false
            summary.subpart = "Teoria"
        }else{
            summary.startDate = course?.labPart?.startHour?: Calendar.getInstance()
            summary.endDate= course?.labPart?.endHour?: Calendar.getInstance()
            summary.isFinished = false
            summary.subpart = "Laboratorio"
        }
        return summary
    }

    override fun getCourseSummary(token:String,idCourse: Int,idUser: Int): CourseSummary? {
        val authorizationHeader = "Bearer $token"
        var course:Course = Course()
        when (idCourse) {
            1 -> {
                course = sd
            }
            2 -> {
                course = met
            }
            3 -> {
                course = ingInfo
            }
            4 -> {
                course = si
            }
            5 -> {
                course = audi
            }
            6 -> {
                course = moviles
            }
            7 -> {
                course = moviles
            }
            8 -> {
                course = calculo
            }
            9 -> {
                course = mkt
            }

        }
        var summary:CourseSummary = CourseSummary()
        summary.idCourse = course?.idCourse ?: 0
        summary.courseName = course?.courseName?:""
        summary.section = course?.section?:0
        summary.startDate = course?.theoryPart?.startHour?: Calendar.getInstance()
        summary.endDate= course?.labPart?.endHour?: Calendar.getInstance()

        return summary
    }

    override fun isToday(token:String,idCourse: Int,idUser: Int): Boolean {
        val authorizationHeader = "Bearer $token"
        val now = Calendar.getInstance(timeZone)
        var today = false
        var course:Course = Course()
        when (idCourse) {
            1 -> {
                course = sd
            }
            2 -> {
                course = met
            }
            3 -> {
                course = ingInfo
            }
            4 -> {
                course = si
            }
            5 -> {
                course = audi
            }
            6 -> {
                course = moviles
            }
            7 -> {
                course = moviles
            }
            8 -> {
                course = calculo
            }
            9 -> {
                course = mkt
            }

        }
        return course.startDate?.get(Calendar.DAY_OF_WEEK)== now.get(Calendar.DAY_OF_WEEK)
    }

    override fun getCourseCardInfo(token:String,idCourse: Int,idUser: Int, isLabo: Int): Pair<String, String> {
        val authorizationHeader = "Bearer $token"
        var pair = Pair("","")
        var course:Course = Course()
        when (idCourse) {
            1 -> {
                course = sd
            }
            2 -> {
                course = met
            }
            3 -> {
                course = ingInfo
            }
            4 -> {
                course = si
            }
            5 -> {
                course = audi
            }
            6 -> {
                course = moviles
            }
            7 -> {
                course = moviles
            }
            8 -> {
                course = calculo
            }
            9 -> {
                course = mkt
            }

        }
        return if(isLabo == 0){//change?
            Pair(course.courseName, "${course.theoryPart.room.typeRoom} ${course.theoryPart.room.idRoom}-${course.theoryPart.room.pavilion}" )
        }else{
            Pair(course.courseName, "${course.labPart.room.typeRoom} ${course.labPart.room.idRoom}-${course.labPart.room.pavilion}" )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getCourseInfoFromTime(token:String, idUser: Int, specificDate: LocalDate): MutableList<Course>? {
        val authorizationHeader = "Bearer $token"
        var list : MutableList<Course> = mutableListOf()

        if(idUser !=5){
            when(specificDate.dayOfWeek){
                DayOfWeek.MONDAY-> {
                    list.add(sd)
                    list.add(met)
                }
                DayOfWeek.TUESDAY-> {
                    list.add(ingInfo)
                    list.add(si)
                }
                DayOfWeek.WEDNESDAY-> {
                    list.add(gdp)
                }
                DayOfWeek.THURSDAY-> {

                }
                DayOfWeek.FRIDAY-> {
                    list.add(audi)
                }
                DayOfWeek.SATURDAY-> {
                    list.add(moviles)
                    list.add(calculo)
                    list.add(mkt)
                }
                DayOfWeek.SUNDAY-> {

                }
            }
        }else{
            when(specificDate.dayOfWeek){
                DayOfWeek.MONDAY-> {

                }
                DayOfWeek.TUESDAY-> {

                }
                DayOfWeek.WEDNESDAY-> {

                }
                DayOfWeek.THURSDAY-> {

                }
                DayOfWeek.FRIDAY-> {

                }
                DayOfWeek.SATURDAY-> {
                    list.add(calculo)
                    list.add(mkt)
                }
                DayOfWeek.SUNDAY-> {

                }
            }
        }
        return list
    }


}