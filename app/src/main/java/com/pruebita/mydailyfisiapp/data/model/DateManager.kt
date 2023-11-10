package com.pruebita.mydailyfisiapp.data.model

import java.util.Calendar

class DateManager {
    val currentTime = Calendar.getInstance()
    private val dayOfWeek = arrayOf("Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado")
    private val monthName = arrayOf(
        "Enero",
        "Febrero",
        "Marzo",
        "Abril",
        "Mayo",
        "Junio",
        "Julio",
        "Agosto",
        "Septiembre",
        "Octubre",
        "Noviembre",
        "Diciembre"
    )

    fun getCurrentDay():Int{
        return currentTime.get(Calendar.DAY_OF_MONTH)
    }
    fun getCurrentMonthNum():Int{
        return currentTime.get(Calendar.MONTH) + 1
    }
    fun getCurrentMonth():String{
        val index = currentTime.get(Calendar.MONTH)
        return monthName[index]
    }
    fun getCurrentYear():Int{
        return currentTime.get(Calendar.YEAR)
    }

    fun getCurrentDayOfWeek():String{
        val index = currentTime.get(Calendar.DAY_OF_WEEK)
        return dayOfWeek[index]
    }

    fun getNowInMillis():Long{
        return currentTime.timeInMillis
    }

}