package com.pruebita.mydailyfisiapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone
import java.util.Timer
import java.util.TimerTask
import javax.inject.Inject

class ClockViewModel : ViewModel() {
    private val _currentTime = MutableLiveData<String>()
    val currentTime: LiveData<String> = _currentTime

    init {
        val timeZone = TimeZone.getTimeZone("America/Lima")
        val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        dateFormat.timeZone = timeZone
        val timeZoneId = timeZone.id
        println("TimeZone ID: $timeZoneId")
        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val calendar = Calendar.getInstance(timeZone)
                val time = dateFormat.format(calendar.time)
                _currentTime.postValue(time)
            }
        }, 0, 1000) // Actualizar cada segundo
    }
}