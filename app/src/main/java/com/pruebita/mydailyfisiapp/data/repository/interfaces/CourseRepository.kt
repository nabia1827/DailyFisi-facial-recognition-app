package com.pruebita.mydailyfisiapp.data.repository.interfaces

import com.pruebita.mydailyfisiapp.data.model.domain.Course
import com.pruebita.mydailyfisiapp.data.model.domain.CourseSummary
import com.pruebita.mydailyfisiapp.data.model.domain.SubPart
import com.pruebita.mydailyfisiapp.data.model.domain.SubPartSummary
import kotlinx.datetime.LocalDate

interface CourseRepository {
    fun getTodayCourses(idUser: Int):MutableList<Course>

    fun getUserCourses(idUser: Int):MutableList<Course>

    fun getCourseShortInfo(idCourse: Int): Course

    fun getSubPartSummary(idCourse: Int, idSubPart: Int): SubPartSummary

    fun getCourseSummary(idCourse: Int):CourseSummary

    fun isToday(idCourse: Int):Boolean

    fun getCourseCardInfo (idCourse: Int, isLabo: Int): Pair<String, String>
    fun getCourseInfoFromTime(specificDate: LocalDate): MutableList<Course>
}