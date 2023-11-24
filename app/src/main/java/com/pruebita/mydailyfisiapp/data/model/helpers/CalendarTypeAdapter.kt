package com.pruebita.mydailyfisiapp.data.model.helpers

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class CalendarTypeAdapter : JsonDeserializer<Calendar>, JsonSerializer<Calendar> {
    val timeZone = TimeZone.getTimeZone("America/Lima")
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())


    override fun serialize(
        src: Calendar?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        dateFormat.timeZone = timeZone
        return JsonPrimitive(dateFormat.format(src?.time))
    }

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Calendar {
        dateFormat.timeZone = timeZone
        val date = dateFormat.parse(json?.asString)
        val calendar = Calendar.getInstance()
        calendar.time = date
        return calendar
    }
}