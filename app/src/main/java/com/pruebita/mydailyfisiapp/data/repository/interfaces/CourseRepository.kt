package com.pruebita.mydailyfisiapp.data.repository.interfaces

import com.pruebita.mydailyfisiapp.data.model.domain.Course
import com.pruebita.mydailyfisiapp.data.model.domain.CourseSummary
import com.pruebita.mydailyfisiapp.data.model.domain.SubPart
import com.pruebita.mydailyfisiapp.data.model.domain.SubPartSummary
import kotlinx.datetime.LocalDate

interface CourseRepository {
    fun getTodayCourses(token:String,idUser: Int):MutableList<Course>?

    fun getUserCourses(token:String,idUser: Int):MutableList<Course>?

    fun getCourseShortInfo(token:String,idCourse: Int,idUser: Int): Course?

    fun getSubPartSummary(token:String,idCourse: Int, idSubPart: Int,idUser: Int): SubPartSummary?

    fun getCourseSummary(token:String,idCourse: Int,idUser: Int):CourseSummary?

    fun isToday(token:String,idCourse: Int,idUser: Int):Boolean

    fun getCourseCardInfo (token:String,idCourse: Int,idUser: Int, isLabo: Int): Pair<String, String>
    fun getCourseInfoFromTime(token:String,idUser: Int,specificDate: LocalDate): MutableList<Course>?
}