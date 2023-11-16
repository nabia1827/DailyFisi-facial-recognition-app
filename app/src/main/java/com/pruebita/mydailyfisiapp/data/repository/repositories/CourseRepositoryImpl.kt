package com.pruebita.mydailyfisiapp.data.repository.repositories

import com.pruebita.mydailyfisiapp.data.model.domain.Course
import com.pruebita.mydailyfisiapp.data.model.domain.Room
import com.pruebita.mydailyfisiapp.data.model.domain.SubPart
import com.pruebita.mydailyfisiapp.data.repository.interfaces.CourseRepository
import java.util.Calendar
import java.util.TimeZone

class CourseRepositoryImpl: CourseRepository {
    override fun getTodayCourses(idUser: Int): MutableList<Course> {
        val timeZone = TimeZone.getTimeZone("America/Lima")
        // API has to return today courses later than actual hour
        val initMin = 25
        val initHour = 12
        val day = 13

        val calculoTeoStart = Calendar.getInstance(timeZone)
        calculoTeoStart.set(2023, Calendar.NOVEMBER, day, initHour, initMin, 0)
        val calculoTeoEnd = Calendar.getInstance(timeZone)
        calculoTeoEnd.set(2023, Calendar.NOVEMBER, day, initHour, initMin+1, 0)

        val calculoLabStart = Calendar.getInstance(timeZone)
        calculoLabStart.set(2023, Calendar.NOVEMBER, day, initHour, initMin+1, 0)
        val calculoLabEnd = Calendar.getInstance(timeZone)
        calculoLabEnd.set(2023, Calendar.NOVEMBER, day, initHour, initMin+3, 0)


        val algoTeoStart = Calendar.getInstance(timeZone)
        algoTeoStart.set(2023, Calendar.NOVEMBER, day, initHour, initMin+3, 0)
        val algoTeoEnd = Calendar.getInstance(timeZone)
        algoTeoEnd.set(2023, Calendar.NOVEMBER, day, initHour, initMin+4, 0)

        val algoLabStart = Calendar.getInstance(timeZone)
        algoLabStart.set(2023, Calendar.NOVEMBER, day, initHour, initMin +4, 0)
        val algoLabEnd = Calendar.getInstance(timeZone)
        algoLabEnd.set(2023, Calendar.NOVEMBER, day, initHour, initMin +6, 0)


        val marketingTeoStart = Calendar.getInstance(timeZone)
        marketingTeoStart.set(2023, Calendar.NOVEMBER, day, initHour, initMin+6, 0)
        val marketingTeoEnd = Calendar.getInstance(timeZone)
        marketingTeoEnd.set(2023, Calendar.NOVEMBER, day, initHour, initMin+7, 0)

        val marketingLabStart = Calendar.getInstance(timeZone)
        marketingLabStart.set(2023, Calendar.NOVEMBER, day, initHour, initMin+7, 0)
        val marketingLabEnd = Calendar.getInstance(timeZone)
        marketingLabEnd.set(2023, Calendar.NOVEMBER, day, initHour, initMin+9, 0)



        return mutableListOf(
            Course(
                idCourse = 1,
                courseName = "Algebra",
                startDate = Calendar.getInstance(),
                endDate = Calendar.getInstance(),
                term = "X",
                theoryPart = SubPart(
                    idPart = 1,
                    desPart = "Teoria",
                    startHour = calculoTeoStart,
                    endHour = calculoTeoEnd,
                    room = Room(
                        idRoom = 102,
                        typeRoom = "Salon",
                        pavilion = "AP",
                        floor = 1,
                        posX = 200,
                        posY = 200,
                    )
                ),
                labPart = SubPart(
                    idPart = 2,
                    desPart = "Laboratorio",
                    startHour = calculoLabStart,
                    endHour = calculoLabEnd,
                    room = Room(
                        idRoom = 5,
                        typeRoom = "Lab",
                        pavilion = "NP",
                        floor = 2,
                        posX = 200,
                        posY = 200,
                    )
                ),
                teacherFullName = "Oswaldo Lopez Michellini",
                section = 1
            ),
            Course(
                idCourse = 2,
                courseName = "Algoritmica I",
                startDate = Calendar.getInstance(),
                endDate = Calendar.getInstance(),
                term = "X",
                theoryPart = SubPart(
                    idPart = 1,
                    desPart = "Teoria",
                    startHour = algoTeoStart,
                    endHour = algoTeoEnd,
                    room = Room(
                        idRoom = 4,
                        typeRoom = "Lab",
                        pavilion = "NP",
                        floor = 1,
                        posX = 200,
                        posY = 200,
                    )
                ),
                labPart = SubPart(
                    idPart = 2,
                    desPart = "Lab.",
                    startHour = algoLabStart,
                    endHour = algoLabEnd,
                    room = Room(
                        idRoom = 7,
                        typeRoom = "Lab",
                        pavilion = "NP",
                        floor = 2,
                        posX = 200,
                        posY = 200,
                    )
                ),
                teacherFullName = "Andres Huertas Sanchez",
                section = 1
            ),
            Course(
                idCourse = 3,
                courseName = "Marketing",
                startDate = Calendar.getInstance(),
                endDate = Calendar.getInstance(),
                term = "X",
                theoryPart = SubPart(
                    idPart = 1,
                    desPart = "Teoria",
                    startHour = marketingTeoStart,
                    endHour = marketingTeoEnd,
                    room = Room(
                        idRoom = 4,
                        typeRoom = "Lab",
                        pavilion = "NP",
                        floor = 1,
                        posX = 200,
                        posY = 200,
                    )
                ),
                labPart = SubPart(
                    idPart = 2,
                    desPart = "Lab.",
                    startHour = marketingLabStart,
                    endHour = marketingLabEnd,
                    room = Room(
                        idRoom = 7,
                        typeRoom = "Lab",
                        pavilion = "NP",
                        floor = 2,
                        posX = 200,
                        posY = 200,
                    )
                ),
                teacherFullName = "Andres Huertas Sanchez",
                section = 1
            )

        )
    }

    override fun getUserCourses(idUser: Int): MutableList<Course> {
        return mutableListOf(
            Course(courseName = "Algoritmica I", section = 1),
            Course(courseName = "Base de Datos", section = 2),
            Course(courseName = "IoT", section = 3),
            Course(courseName = "Marketing", section = 4),
            Course(courseName = "Calculo", section = 1),
            Course(courseName = "Algebra", section = 2)

        )
    }

    override fun getCourseShortInfo(idCourse: Int): Course {
        return Course()
    }
}