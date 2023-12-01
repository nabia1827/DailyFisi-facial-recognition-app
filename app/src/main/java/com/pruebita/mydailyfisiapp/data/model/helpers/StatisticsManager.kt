package com.pruebita.mydailyfisiapp.data.model.helpers

import com.pruebita.mydailyfisiapp.data.model.domain.Course
import com.pruebita.mydailyfisiapp.data.source.audi
import com.pruebita.mydailyfisiapp.data.source.calculo
import com.pruebita.mydailyfisiapp.data.source.ingInfo
import com.pruebita.mydailyfisiapp.data.source.met
import com.pruebita.mydailyfisiapp.data.source.moviles
import com.pruebita.mydailyfisiapp.data.source.sd
import com.pruebita.mydailyfisiapp.data.source.si
import com.pruebita.mydailyfisiapp.data.source.student1Global
import com.pruebita.mydailyfisiapp.data.source.teacherGlobalReportCalculo
import com.pruebita.mydailyfisiapp.data.source.teacherGlobalReportMkt

class StatisticsManager {


    fun getTotalAssistClasses(idUser:Int):Int{
        if(idUser != 5){
            val ct1 = student1Global.assSd.count {it.theoryAssist == true}
            val ct2 = student1Global.assMet.count {it.theoryAssist == true}
            val ct3 = student1Global.assIng.count {it.theoryAssist == true}
            val ct4 = student1Global.assSi.count {it.theoryAssist == true}
            val ct5 = student1Global.assGdp.count {it.theoryAssist == true}
            val ct6 = student1Global.assAudi.count {it.theoryAssist == true}
            val ct7 = student1Global.assMoviles.count {it.theoryAssist == true}
            val ct8 = student1Global.assCalculo.count {it.theoryAssist == true}
            val ct9 = student1Global.assMkt.count {it.theoryAssist== true}

            val ct = ct1 + ct2 + ct3 + ct4 + ct5 + ct6 +ct7 + ct8 + ct9

            val cl1 = student1Global.assSd.count {it.labAssist == true}
            val cl2 = student1Global.assMet.count {it.labAssist == true}
            val cl3 = student1Global.assIng.count {it.labAssist == true}
            val cl4 = student1Global.assSi.count {it.labAssist == true}
            val cl5 = student1Global.assGdp.count {it.theoryAssist == true}
            val cl6 = student1Global.assAudi.count {it.theoryAssist== true}
            val cl7 = student1Global.assMoviles.count {it.theoryAssist == true}
            val cl8 = student1Global.assCalculo.count {it.theoryAssist == true}
            val cl9 = student1Global.assMkt.count {it.theoryAssist == true}

            val cl = cl1 + cl2 + cl3 + cl4 + cl5 + cl6 +cl7 + cl8 + cl9

            return ct + cl
        }else{
            val c1 = teacherGlobalReportCalculo.theoryAssist.sumOf { it }
            val c2 = teacherGlobalReportCalculo.labAssist.sumOf { it }
            val m1 = teacherGlobalReportMkt.theoryAssist.sumOf { it }
            val m2 = teacherGlobalReportMkt.labAssist.sumOf { it }

            return c1+c2+m1+m2
        }
    }

    fun getTotalClasses(idUser:Int): Int{

        if(idUser !=5){
            val ct1 = student1Global.assSd.count {it.theoryAssist!=null}
            val ct2 = student1Global.assMet.count {it.theoryAssist!=null}
            val ct3 = student1Global.assIng.count {it.theoryAssist!=null}
            val ct4 = student1Global.assSi.count {it.theoryAssist!=null}
            val ct5 = student1Global.assGdp.count {it.theoryAssist!=null}
            val ct6 = student1Global.assAudi.count {it.theoryAssist!=null}
            val ct7 = student1Global.assMoviles.count {it.theoryAssist!=null}
            val ct8 = student1Global.assCalculo.count {it.theoryAssist!=null}
            val ct9 = student1Global.assMkt.count {it.theoryAssist!=null}

            val ct = ct1 + ct2 + ct3 + ct4 + ct5 + ct6 +ct7 + ct8 + ct9

            val cl1 = student1Global.assSd.count {it.labAssist!=null}
            val cl2 = student1Global.assMet.count {it.labAssist!=null}
            val cl3 = student1Global.assIng.count {it.labAssist!=null}
            val cl4 = student1Global.assSi.count {it.labAssist!=null}
            val cl5 = student1Global.assGdp.count {it.theoryAssist!=null}
            val cl6 = student1Global.assAudi.count {it.theoryAssist!=null}
            val cl7 = student1Global.assMoviles.count {it.theoryAssist!=null}
            val cl8 = student1Global.assCalculo.count {it.theoryAssist!=null}
            val cl9 = student1Global.assMkt.count {it.theoryAssist!=null}

            val cl = cl1 + cl2 + cl3 + cl4 + cl5 + cl6 +cl7 + cl8 + cl9
            return ct + cl
        }else{
            return 5*2 //hate u Kotlin
        }
    }

    fun getTotalClassesCourse(idCourse: Int,idUser:Int): Int{
        var totalClasses = 0

        if(idUser != 5){
            when (idCourse) {
                1 -> {
                    totalClasses = student1Global.assSd.count {it.theoryAssist!=null} + student1Global.assSd.count {it.labAssist!=null}
                    println("EST: $totalClasses")
                }
                2 -> {
                    totalClasses = student1Global.assMet.count {it.theoryAssist!=null} + student1Global.assMet.count {it.labAssist!=null}
                }
                3 -> {
                    totalClasses = student1Global.assIng.count {it.theoryAssist!=null} + student1Global.assIng.count {it.labAssist!=null}
                }
                4 -> {
                    totalClasses = student1Global.assSi.count {it.theoryAssist!=null} + student1Global.assSi.count {it.labAssist!=null}
                }
                5 -> {
                    totalClasses = student1Global.assGdp.count {it.theoryAssist!=null} + student1Global.assGdp.count {it.labAssist!=null}
                }
                6 -> {
                    totalClasses = student1Global.assAudi.count {it.theoryAssist!=null} + student1Global.assAudi.count {it.labAssist!=null}
                }
                7 -> {
                    totalClasses = student1Global.assMoviles.count {it.theoryAssist!=null} + student1Global.assMoviles.count {it.labAssist!=null}
                }
                8 -> {
                    totalClasses = student1Global.assCalculo.count {it.theoryAssist!=null} + student1Global.assCalculo.count {it.labAssist!=null}
                }
                9 -> {
                    totalClasses = student1Global.assMkt.count {it.theoryAssist!=null} + student1Global.assMkt.count {it.labAssist!=null}
                }

            }
            return totalClasses
        }else{
            return 5*2
        }

    }

    fun getTotalAssistClassesCourse(idCourse: Int,idUser:Int): Int{
        var totalAssistClasses = 0

        if(idUser != 5){
            when (idCourse) {
                1 -> {
                    totalAssistClasses = student1Global.assSd.count {it.theoryAssist==true} + student1Global.assSd.count {it.labAssist==true}
                    println("EST num: $totalAssistClasses")
                }
                2 -> {
                    totalAssistClasses = student1Global.assMet.count {it.theoryAssist==true} + student1Global.assMet.count {it.labAssist==true}
                }
                3 -> {
                    totalAssistClasses = student1Global.assIng.count {it.theoryAssist==true} + student1Global.assIng.count {it.labAssist==true}
                }
                4 -> {
                    totalAssistClasses = student1Global.assSi.count {it.theoryAssist==true} + student1Global.assSi.count {it.labAssist==true}
                }
                5 -> {
                    totalAssistClasses = student1Global.assGdp.count {it.theoryAssist==true} + student1Global.assGdp.count {it.labAssist==true}
                }
                6 -> {
                    totalAssistClasses = student1Global.assAudi.count {it.theoryAssist==true} + student1Global.assAudi.count {it.labAssist==true}
                }
                7 -> {
                    totalAssistClasses = student1Global.assMoviles.count {it.theoryAssist==true} + student1Global.assMoviles.count {it.labAssist==true}
                }
                8 -> {
                    totalAssistClasses = student1Global.assCalculo.count {it.theoryAssist==true} + student1Global.assCalculo.count {it.labAssist==true}
                }
                9 -> {
                    totalAssistClasses = student1Global.assMkt.count {it.theoryAssist==true} + student1Global.assMkt.count {it.labAssist==true}
                }

            }
        }
        else{
            if(idCourse == 8){
                val c1 = teacherGlobalReportCalculo.theoryAssist.sumOf { it }
                val c2 = teacherGlobalReportCalculo.labAssist.sumOf { it }
                totalAssistClasses = c1 + c2
            }else{
                val m1 = teacherGlobalReportMkt.theoryAssist.sumOf { it }
                val m2 = teacherGlobalReportMkt.labAssist.sumOf { it }
                totalAssistClasses = m1 + m2
            }
        }

        return totalAssistClasses

    }


}