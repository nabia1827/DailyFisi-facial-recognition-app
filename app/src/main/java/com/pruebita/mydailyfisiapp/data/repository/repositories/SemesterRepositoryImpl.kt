package com.pruebita.mydailyfisiapp.data.repository.repositories

import com.pruebita.mydailyfisiapp.data.model.domain.Semester
import com.pruebita.mydailyfisiapp.data.repository.interfaces.SemesterRepository

class SemesterRepositoryImpl:SemesterRepository {

    override fun getCurrentSemester():Semester{
        return Semester(
            idSemester = 1,
            semesterDesc = "2023-II",
            null,
            null
        )
    }

    override fun getCurrentWeek():Int{
        return 7
    }
}