package com.pruebita.mydailyfisiapp.data.repository.repositories

import com.pruebita.mydailyfisiapp.data.model.domain.Attendance
import com.pruebita.mydailyfisiapp.data.model.domain.Course
import com.pruebita.mydailyfisiapp.data.model.domain.CourseReport
import com.pruebita.mydailyfisiapp.data.model.domain.DailyCourseAssist
import com.pruebita.mydailyfisiapp.data.model.domain.StudentAssistUnit
import com.pruebita.mydailyfisiapp.data.model.domain.UpdatedReport
import com.pruebita.mydailyfisiapp.data.model.helpers.StatisticsManager
import com.pruebita.mydailyfisiapp.data.repository.interfaces.AttendanceRepository
import com.pruebita.mydailyfisiapp.data.source.*

import java.util.Calendar
import java.util.TimeZone

class AttendanceRepositoryImpl:AttendanceRepository {
    private val initMin = 14
    private val initHour = 8
    val day = 25
    private val timeZone: TimeZone = TimeZone.getTimeZone("America/Lima")
    private val st:StatisticsManager = StatisticsManager()
    override fun getTodayAssists(idUser: Int): MutableList<Attendance> {
        val now = Calendar.getInstance(timeZone)
        var list : MutableList<Course> = mutableListOf()
        if(idUser !=5){
            when(now.get(Calendar.DAY_OF_WEEK)){
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
            when(now.get(Calendar.DAY_OF_WEEK)){
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
        var assists : MutableList<Attendance> = mutableListOf()
        for (i in 0 until list.size){
            var attTheo = Attendance(
                date = now,
                idCourse = list[i].idCourse,
                idSubPart = 0,
                courseName = list[i].courseName,
                coursePart = list[i].theoryPart.desPart,
                courseRoom = "${list[i].theoryPart.room.idRoom} - ${list[i].theoryPart.room.pavilion}",
                startTime = list[i].theoryPart.startHour,
                endTime = list[i].theoryPart.endHour,
                state = 5
            )
            var attLab = Attendance(
                date = now,
                idCourse = list[i].idCourse,
                idSubPart = 0,
                courseName = list[i].courseName,
                coursePart = list[i].labPart.desPart,
                courseRoom = "${list[i].labPart.room.idRoom} - ${list[i].labPart.room.pavilion}",
                startTime = list[i].labPart.startHour,
                endTime = list[i].labPart.endHour,
                state = 5
            )
            assists.add(attTheo)
            assists.add(attLab)
        }

        return assists
    }

    override fun isAttendanceOpen(idCourse: Int): Int {
        // Azure stuff
        return 2
    }

    override fun getTotalPercentageAttendance(idUser: Int): Int {
        val totalClasses = st.getTotalClasses(idUser)
        val totalAssistClasses = st.getTotalAssistClasses(idUser)
        println(" total: $totalAssistClasses/$totalClasses")
        return totalAssistClasses/totalClasses
    }

    override fun getCourseReports(idUser: Int): MutableList<CourseReport> {
        val now = Calendar.getInstance(timeZone)
        val listCourses: MutableList<Course> = mutableListOf(sd,met, ingInfo, si, gdp, audi, moviles,
            calculo, mkt)
        val listReports:MutableList<CourseReport> = mutableListOf()
        for (i in 0 until listCourses.size){
            val totalClassesCourse = st.getTotalClassesCourse(listCourses[i].idCourse,idUser)
            val totalAssistClassesCourse = st.getTotalAssistClassesCourse(listCourses[i].idCourse,idUser)
            print("course $i es $totalAssistClassesCourse/$totalClassesCourse")
            var report = CourseReport(
                idCourse = listCourses[i].idCourse,
                section = listCourses[i].section,
                courseName = listCourses[i].courseName,
                startTime = listCourses[i].theoryPart.startHour,
                endTime = listCourses[i].labPart.endHour,
                totalAssist = totalAssistClassesCourse,
                percentage = (totalAssistClassesCourse*100.0/totalClassesCourse).toInt()
            )
            println("${(totalAssistClassesCourse/totalClassesCourse*100)}")
            listReports.add(report)
        }

        return listReports
    }

    override fun getTodayCourses(idUser: Int): MutableList<Int> {
        val list:MutableList<Int> = mutableListOf()
        val calendar = Calendar.getInstance(timeZone)

        if(idUser !=5){
            when(calendar.get(Calendar.DAY_OF_WEEK)){
                Calendar.MONDAY-> {
                    list.add(sd.idCourse-1)
                    list.add(met.idCourse-1)
                }
                Calendar.TUESDAY-> {
                    list.add(ingInfo.idCourse-1)
                    list.add(si.idCourse-1)
                }
                Calendar.WEDNESDAY-> {
                    list.add(gdp.idCourse-1)
                }
                Calendar.THURSDAY-> {

                }
                Calendar.FRIDAY-> {
                    list.add(audi.idCourse-1)
                }
                Calendar.SATURDAY-> {
                    list.add(moviles.idCourse-1)
                    list.add(calculo.idCourse-1)
                    list.add(mkt.idCourse-1)
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
                    list.add(calculo.idCourse-1)
                    list.add(mkt.idCourse-1)
                }
                Calendar.SUNDAY-> {

                }
            }
        }
        return list //Positions not ID
    }

    override fun getTotalFraction(idUser: Int): Pair<Int, Int> {
        val totalClasses = st.getTotalClasses(idUser)
        val totalAssistClasses = st.getTotalAssistClasses(idUser)
        return Pair(totalAssistClasses,totalClasses)//Change
    }

    override fun getUpdatedCourseReport(idUser: Int, idCourse: Int): UpdatedReport? {
        val totalClasses = st.getTotalClasses(idUser)
        val totalAssistClasses = st.getTotalAssistClasses(idUser)
        val totalClassesCourse = st.getTotalClassesCourse(idCourse,idUser)
        val totalAssistClassesCourse = st.getTotalAssistClassesCourse(idCourse,idUser)

        return UpdatedReport(
            percentage = (totalAssistClasses* 100.0/totalClasses ).toInt(),
            totalAssistClasses = totalAssistClasses,
            totalClasses = totalClasses,
            totalCourse = totalAssistClassesCourse,
            percentageCourse = (totalAssistClassesCourse* 100.0/totalClassesCourse).toInt()
        )
    }

    override fun getUserCourseAttendance(
        idUser: Int,
        idCourse: Int
    ): MutableList<DailyCourseAssist> {
        val now = Calendar.getInstance(timeZone)
        val firstChange = Calendar.getInstance(timeZone)
        firstChange.set(2023, Calendar.NOVEMBER, day, initHour, initMin+2, 0)
        var list: MutableList<DailyCourseAssist> = mutableListOf()
        when (idCourse) {
            1 -> {
                list = student1Global.assSd

            }
            2 -> {
                list = student1Global.assMet
            }
            3 -> {
                list = student1Global.assIng
            }
            4 -> {
                list = student1Global.assSi
            }
            5 -> {
                list = student1Global.assGdp
            }
            6 -> {
                list = student1Global.assAudi
            }
            7 -> {
                list = student1Global.assMoviles
            }
            8 -> {
                list = student1Global.assCalculo
            }
            9 -> {
                list = student1Global.assMkt
            }

        }
        return list
    }

    override fun getStudentList(idCourse: Int): MutableList<StudentAssistUnit> {
        var list: MutableList<StudentAssistUnit> = mutableListOf()
        list = if(idCourse == 8){
            teacherGlobalReportCalculo.studentList
        } else{
            teacherGlobalReportMkt.studentList
        }
        return list
    }

    override fun getAttendanceList(idCourse: Int, idSubPart: Int): MutableList<Boolean> {
        //Call azure
        return mutableListOf(false,false,false,false,false,false,false,false,false)

    }

    override fun endAttendance(idCourse: Int, idSubPart: Int) {
        // Send "finish" to azure
    }

    override fun setAttendanceUser(idUser: Int, idCourse: Int, idSubPart: Int, assist: Boolean) {
        when(idCourse){
            1 -> {
                if(idSubPart ==0){
                    student1Global.assSd[student1Global.assSd.size-1].theoryAssist = assist
                }else{
                    student1Global.assSd[student1Global.assSd.size-1].labAssist = assist
                }
            }
            2 -> {
                if(idSubPart ==0){
                    student1Global.assMet[student1Global.assMet.size-1].theoryAssist = assist
                }else{
                    student1Global.assMet[student1Global.assMet.size-1].labAssist = assist
                }
            }
            3 -> {
                if(idSubPart ==0){
                    student1Global.assIng[student1Global.assIng.size-1].theoryAssist = assist
                }else{
                    student1Global.assIng[student1Global.assIng.size-1].labAssist = assist
                }
            }
            4 -> {
                if(idSubPart ==0){
                    student1Global.assSi[student1Global.assSi.size-1].theoryAssist = assist
                }else{
                    student1Global.assSi[student1Global.assSi.size-1].labAssist = assist
                }
            }
            5 -> {
                if(idSubPart ==0){
                    student1Global.assGdp[student1Global.assGdp.size-1].theoryAssist = assist
                }else{
                    student1Global.assGdp[student1Global.assGdp.size-1].labAssist = assist
                }
            }
            6 -> {
                if(idSubPart ==0){
                    student1Global.assAudi[student1Global.assAudi.size-1].theoryAssist = assist
                }else{
                    student1Global.assAudi[student1Global.assAudi.size-1].labAssist = assist
                }
            }
            7 -> {
                if(idSubPart ==0){
                    student1Global.assMoviles[student1Global.assMoviles.size-1].theoryAssist = assist
                }else{
                    student1Global.assMoviles[student1Global.assMoviles.size-1].labAssist = assist
                }
            }
            8 -> {
                if(idSubPart ==0){
                    student1Global.assCalculo[student1Global.assCalculo.size-1].theoryAssist = assist
                    teacherGlobalReportCalculo.theoryAssist[idUser-1] += 1
                }else{
                    student1Global.assCalculo[student1Global.assCalculo.size-1].labAssist = assist
                    teacherGlobalReportCalculo.labAssist[idUser-1] += 1
                }
            }
            9 -> {
                if(idSubPart ==0){
                    student1Global.assMkt[student1Global.assMkt.size-1].theoryAssist = assist
                    teacherGlobalReportMkt.theoryAssist[idUser-1] += 1
                }else{
                    student1Global.assMkt[student1Global.assMkt.size-1].labAssist = assist
                    teacherGlobalReportMkt.labAssist[idUser-1] += 1
                }

            }
        }

    }

    override fun getSectionTheoryReport(idCourse: Int): MutableList<Int> {
        return if(idCourse ==8){
            teacherGlobalReportCalculo.theoryAssist
        }else{
            teacherGlobalReportMkt.theoryAssist
        }
    }

    override fun getSectionLabReport(idCourse: Int): MutableList<Int> {
        return if(idCourse ==8){
            teacherGlobalReportCalculo.labAssist
        }else{
            teacherGlobalReportMkt.labAssist
        }
    }

}