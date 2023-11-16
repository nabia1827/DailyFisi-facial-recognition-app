package com.pruebita.mydailyfisiapp.data.repository.interfaces

import com.pruebita.mydailyfisiapp.data.model.domain.Course

interface CourseRepository {
    fun getTodayCourses(idUser: Int):MutableList<Course>

    fun getUserCourses(idUser: Int):MutableList<Course>

    fun getCourseShortInfo(idCourse: Int): Course
}