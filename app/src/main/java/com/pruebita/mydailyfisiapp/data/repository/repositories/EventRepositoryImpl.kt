package com.pruebita.mydailyfisiapp.data.repository.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pruebita.mydailyfisiapp.data.model.domain.Event
import com.pruebita.mydailyfisiapp.data.model.domain.RecognizeResponse
import com.pruebita.mydailyfisiapp.data.repository.interfaces.ApiService
import com.pruebita.mydailyfisiapp.data.repository.interfaces.EventRepository
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

class EventRepositoryImpl(private val apiService:ApiService): EventRepository {

    override suspend fun listAllTodayEvents(token:String): MutableList<Event>? {
        val authorizationHeader = "Bearer $token"
        println("Bearer in event $token")
        var list:MutableList<Event>? = mutableListOf<Event>()
        try {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
            val timeZone = TimeZone.getTimeZone("America/Lima")
            dateFormat.timeZone = timeZone

            val response = apiService.listAllTodayEvents(authorizationHeader)
            if (response.isSuccessful) {
                list = response.body()
                println("Fecha primera: ${dateFormat.format(list?.get(0)?.eventDate?.time)}")
            } else {
                println("Error api event: ${response.code()}")
            }
        } catch (e: Exception) {
            println("Error api event en catch: $e" )
        }

        return list
    }
}