package com.pruebita.mydailyfisiapp.data.repository.interfaces

import com.pruebita.mydailyfisiapp.data.model.domain.Course
import com.pruebita.mydailyfisiapp.data.model.domain.CourseSummary
import com.pruebita.mydailyfisiapp.data.model.domain.Event
import com.pruebita.mydailyfisiapp.data.model.domain.ProfileUser
import com.pruebita.mydailyfisiapp.data.model.domain.SubPartSummary
import com.pruebita.mydailyfisiapp.data.model.domain.User
import com.pruebita.mydailyfisiapp.data.model.domain.UserSetTime
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Query
import java.util.Calendar

interface ApiService {
    //Event controller
    @GET("api/event")
    suspend fun listAllTodayEvents(@Header("Authorization") authorization: String): Response<MutableList<Event>>

    //User controller
    @GET("api/user")
    suspend fun getUser(
        @Header("Authorization") authorization: String,
        @Query("pUser") pUser: String,
        @Query("pPassword") pPassword: String
    ): Response<User>

    @GET("api/user")
    suspend fun validateUser(
        @Header("Authorization") authorization: String,
        @Query("pUser") pUser: String,
        @Query("pPassword") pPassword: String,
        @Query("validate") validate: Int

    ): Response<Int>

    @GET("api/user")
    suspend fun getUserFromGoogle(
        @Header("Authorization") authorization: String,
        @Query("userName") userName: String
    ): Response<User>

    @PUT("api/user")
    suspend fun updateImageUser(
        @Header("Authorization") authorization: String,
        @Body profileUser: ProfileUser
    ): Response<Int>

    @PUT("api/user")
    suspend fun setActiveSession(
        @Header("Authorization") authorization: String,
        @Query("idUser") idUser: Int,
        @Query("sessionActive") sessionActive: Boolean
    ): Response<Int>

    @GET("api/user")
    suspend fun getUserActive(
        @Header("Authorization") authorization: String,
        @Query("idUser") idUser: Int,
    ): Response<Boolean>

    @PUT("api/user")
    suspend fun setUserActive(
        @Header("Authorization") authorization: String,
        @Query("idUser") idUser: Int,
        @Query("sessionActive") userActive: Boolean,
        @Query("active") active: Int
    ): Response<Int>


    //Course controller
    @GET("api/course")
    suspend fun getTodayCourses(
        @Header("Authorization") authorization: String,
        @Query("pIdUser") pIdUser: Int,
        @Query("pDayOfWeek") pDayOfWeek: Int
    ): Response<MutableList<Course>>

    @GET("api/course")
    suspend fun getUserCourses(
        @Header("Authorization") authorization: String,
        @Query("pIdUser") pIdUser: Int
    ): Response<MutableList<Course>>

    @GET("api/course")
    suspend fun getCourseShortInfo(
        @Header("Authorization") authorization: String,
        @Query("pIdCourse") pIdCourse: Int,
        @Query("pIdUser") pIdUser: Int,
        @Query("type") type: Float
    ): Response<Course>

    @GET("api/course")
    suspend fun getSubPartSummary(
        @Header("Authorization") authorization: String,
        @Query("pIdCourse") pIdCourse: Int,
        @Query("pIdSubPart") pIdSubPart: Int,
        @Query("pIdUser") pIdUser: Int,
        @Query("pWeek") pWeek: Int
    ): Response<SubPartSummary>

    @GET("api/course")
    suspend fun getCourseSummary(
        @Header("Authorization") authorization: String,
        @Query("pIdCourse") pIdCourse: Int,
        @Query("pIdUser") pIdUser: Int,
        @Query("op") op: Boolean
    ): Response<CourseSummary>

    @GET("api/course")
    suspend fun isToday(
        @Header("Authorization") authorization: String,
        @Query("pIdCourse") pIdCourse: Int,
        @Query("pIdUser") pIdUser: Int,
        @Query("today") today: String
    ): Response<Boolean>

    @GET("api/course")
    suspend fun getCourseCardInfo(
        @Header("Authorization") authorization: String,
        @Query("pIdCourse") pIdCourse: Int,
        @Query("pIdUser") pIdUser: Int,
        @Query("pIdSubPart") pIdSubPart: Int
    ): Response<Pair<String,String>>

    @GET("api/course")
    suspend fun getCourseInfoFromTime(
        @Header("Authorization") authorization: String,
        @Query("idUser") pIdUser: Int,
        @Query("actualTime") actualTime: String
    ): Response<MutableList<Course>>






}