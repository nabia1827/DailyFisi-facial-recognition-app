package com.pruebita.mydailyfisiapp.data.repository.repositories

import com.google.gson.JsonPrimitive
import com.pruebita.mydailyfisiapp.data.model.domain.Course
import com.pruebita.mydailyfisiapp.data.model.domain.CourseSummary
import com.pruebita.mydailyfisiapp.data.model.domain.Room
import com.pruebita.mydailyfisiapp.data.model.domain.SubPart
import com.pruebita.mydailyfisiapp.data.model.domain.SubPartSummary
import com.pruebita.mydailyfisiapp.data.model.domain.User
import com.pruebita.mydailyfisiapp.data.model.domain.UserSetTime
import com.pruebita.mydailyfisiapp.data.repository.interfaces.ApiService
import com.pruebita.mydailyfisiapp.data.repository.interfaces.CourseRepository
import kotlinx.datetime.LocalDate
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

class CourseRepositoryImpl(private val apiService: ApiService): CourseRepository {
    private val timeZone: TimeZone = TimeZone.getTimeZone("America/Lima")
    private val initMin = 17
    private val initHour = 13
    val day = 18

    //with order
    override suspend fun getTodayCourses(token:String, idUser: Int): MutableList<Course>? {
        val authorizationHeader = "Bearer $token"
        val calendar = Calendar.getInstance()
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

        var list : MutableList<Course>? = mutableListOf()
        try {
            val response = apiService.getTodayCourses(authorizationHeader,idUser,dayOfWeek)
            if (response.isSuccessful) {
                list = response.body()
                println("getTodayCourses: ${list?.get(0)?.idCourse}")
            } else {
                println("Error api getTodayCourses: ${response.code()}")
            }
        } catch (e: Exception) {
            println("Error api getTodayCourses en catch: $e" )
        }
        return list

    }

    // without order
    override suspend fun getUserCourses(token:String, idUser: Int): MutableList<Course>? {
        val authorizationHeader = "Bearer $token"
        var list : MutableList<Course>? = mutableListOf()
        try {
            val response = apiService.getUserCourses(authorizationHeader,idUser)
            if (response.isSuccessful) {
                list = response.body()
                println("getUserCourses: ${list?.get(0)?.idCourse}")
            } else {
                println("Error api getUserCourses: ${response.code()}")
            }
        } catch (e: Exception) {
            println("Error api getUserCourses en catch: $e" )
        }
        return list
    }

    override suspend fun getCourseShortInfo(token:String,idCourse: Int,idUser: Int): Course? {
        val authorizationHeader = "Bearer $token"
        var course:Course? = Course()
        try {
            val response = apiService.getCourseShortInfo(authorizationHeader,idCourse, idUser,0.0f)
            if (response.isSuccessful) {
                course = response.body()
                println("getUserCourses: ${course?.idCourse}")
            } else {
                println("Error api getUserCourses: ${response.code()}")
            }
        } catch (e: Exception) {
            println("Error api getUserCourses en catch: $e" )
        }
        return course
    }

    override suspend fun getSubPartSummary(token:String,idCourse: Int, idSubPart: Int,idUser: Int): SubPartSummary? {
        val authorizationHeader = "Bearer $token"
        var summary:SubPartSummary? = SubPartSummary()
        try {
            val response = apiService.getSubPartSummary(authorizationHeader,idCourse,idSubPart,idUser,0)
            if (response.isSuccessful) {
                summary = response.body()
                println("getSubPartSummary: ${summary?.courseName}")
            } else {
                println("Error api getSubPartSummary: ${response.code()}")
            }
        } catch (e: Exception) {
            println("Error api getSubPartSummary en catch: $e" )
        }
        return summary
    }

    override suspend fun getCourseSummary(token:String,idCourse: Int,idUser: Int): CourseSummary? {
        val authorizationHeader = "Bearer $token"
        var summary:CourseSummary? = CourseSummary()
        try {
            val response = apiService.getCourseSummary(authorizationHeader,idCourse,idUser,false)
            if (response.isSuccessful) {
                summary = response.body()
                println("getCourseSummary: ${summary?.courseName}")
            } else {
                println("Error api getCourseSummary: ${response.code()}")
            }
        } catch (e: Exception) {
            println("Error api getCourseSummary en catch: $e" )
        }
        return summary
    }

    override suspend fun isToday(token:String,idCourse: Int,idUser: Int): Boolean {
        val authorizationHeader = "Bearer $token"
        var today = false
        try {
            val response = apiService.isToday(authorizationHeader,idCourse,idUser,"a")
            if (response.isSuccessful) {
                today = response.body()?:false
                println("isToday: $today")
            } else {
                println("Error api isToday: ${response.code()}")
            }
        } catch (e: Exception) {
            println("Error api isToday en catch: $e" )
        }
        return today
    }

    override suspend fun getCourseCardInfo(token:String,idCourse: Int,idUser: Int, isLabo: Int): Pair<String, String> {
        val authorizationHeader = "Bearer $token"
        var pair = Pair("","")
        /*
        try { //To change
            val response = apiService.getCourseCardInfo(authorizationHeader,idCourse,idUser, isLabo+1)
            if (response.isSuccessful) {
                pair = response.body()?:Pair("","")
                println("getCourseCardInfo: ${pair.first} - ${pair.second}")
            } else {
                println("Error api getCourseCardInfo: ${response.code()}")
            }
        } catch (e: Exception) {
            println("Error api getCourseCardInfo en catch: $e" )
        }*/
        return Pair("Moviles","Lab 04-NP")
    }

    override suspend fun getCourseInfoFromTime(token:String,idUser: Int,specificDate: LocalDate): MutableList<Course>? {
        val authorizationHeader = "Bearer $token"
        val calendar = Calendar.getInstance()
        calendar.clear()
        calendar.set(specificDate.year, specificDate.monthNumber-1, specificDate.dayOfMonth)
        val timeZone = TimeZone.getTimeZone("America/Lima")

        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())

        var list : MutableList<Course>? = mutableListOf()
        try {
            dateFormat.timeZone = timeZone
            println("String date:" + dateFormat.format(calendar?.time))
            println(JsonPrimitive("Json date:" + dateFormat.format(calendar?.time)))
            val response = apiService.getCourseInfoFromTime(authorizationHeader, idUser,dateFormat.format(calendar?.time))


            if (response.isSuccessful) {
                list = response.body()
                println("getCourseInfoFromTime: ${list?.get(0)?.idCourse}")
            } else {
                println("Error api getCourseInfoFromTime: ${response.code()}")
            }
        } catch (e: Exception) {
            println("Error api getCourseInfoFromTime en catch: $e" )
        }
        return list
    }


}