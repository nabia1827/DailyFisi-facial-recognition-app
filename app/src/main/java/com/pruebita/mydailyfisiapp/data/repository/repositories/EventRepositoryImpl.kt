package com.pruebita.mydailyfisiapp.data.repository.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pruebita.mydailyfisiapp.data.model.domain.Event
import com.pruebita.mydailyfisiapp.data.model.domain.RecognizeResponse
import com.pruebita.mydailyfisiapp.data.repository.interfaces.ApiService
import com.pruebita.mydailyfisiapp.data.repository.interfaces.EventRepository
import com.pruebita.mydailyfisiapp.data.source.audi
import com.pruebita.mydailyfisiapp.data.source.*
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

    override fun listAllTodayEvents(token:String): MutableList<Event>? {
        val authorizationHeader = "Bearer $token"
        println("Bearer in event $token")
        var list:MutableList<Event> = mutableListOf<Event>()
        val calendar = Calendar.getInstance()

        when(calendar.get(Calendar.DAY_OF_WEEK)){
            Calendar.FRIDAY->{
                list.add(event1)
            }
            Calendar.SATURDAY->{
                list.add(event2)
                list.add(event3)
            }
        }
        return list
    }
}