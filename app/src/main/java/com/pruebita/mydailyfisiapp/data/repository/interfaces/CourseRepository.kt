package com.pruebita.mydailyfisiapp.data.repository.interfaces

import com.pruebita.mydailyfisiapp.data.model.domain.Course
import com.pruebita.mydailyfisiapp.data.model.domain.CourseSummary
import com.pruebita.mydailyfisiapp.data.model.domain.SubPart
import com.pruebita.mydailyfisiapp.data.model.domain.SubPartSummary
import kotlinx.datetime.LocalDate

interface CourseRepository {
    suspend fun getTodayCourses(token:String,idUser: Int):MutableList<Course>?

    suspend fun getUserCourses(token:String,idUser: Int):MutableList<Course>?

    suspend fun getCourseShortInfo(token:String,idCourse: Int,idUser: Int): Course?

    suspend fun getSubPartSummary(token:String,idCourse: Int, idSubPart: Int,idUser: Int): SubPartSummary?

    suspend fun getCourseSummary(token:String,idCourse: Int,idUser: Int):CourseSummary?

    suspend fun isToday(token:String,idCourse: Int,idUser: Int):Boolean

    suspend fun getCourseCardInfo (token:String,idCourse: Int,idUser: Int, isLabo: Int): Pair<String, String>
    suspend fun getCourseInfoFromTime(token:String,idUser: Int,specificDate: LocalDate): MutableList<Course>?
}