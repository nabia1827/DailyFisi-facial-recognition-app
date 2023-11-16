package com.pruebita.mydailyfisiapp.data.model.helpers

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

class DateManager {
    private val timeZone: TimeZone = TimeZone.getTimeZone("America/Lima")
    private val currentTime: Calendar = Calendar.getInstance(timeZone)
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
        return dayOfWeek[index - 1]
    }

    fun getNowInMillis():Long{
        return currentTime.timeInMillis
    }

    fun getHourString(hour: Calendar): String {
        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        dateFormat.timeZone = timeZone
        return dateFormat.format(hour.time)
    }

    fun getShortDateString(date: Calendar?): String {
        val dateFormat = SimpleDateFormat("dd/MM/yy", Locale.getDefault())
        dateFormat.timeZone = timeZone
        if(date !=null){
            return dateFormat.format(date.time)
        }
        else{
            return "-/-/-"
        }

    }

}