package com.pruebita.mydailyfisiapp.data.repository.interfaces

import com.pruebita.mydailyfisiapp.data.model.domain.Course
import kotlinx.datetime.LocalDate

interface CourseRepository {
    fun getTodayCourses(idUser: Int):MutableList<Course>

    fun getUserCourses(idUser: Int):MutableList<Course>

    fun getCourseShortInfo(idCourse: Int): Course

    fun getCourseCardInfo (idCourse: Int, isLabo: Int): Pair<String, String>
    fun getCourseInfoFromTime(specificDate: LocalDate): MutableList<Course>
}