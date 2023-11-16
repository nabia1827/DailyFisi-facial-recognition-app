package com.pruebita.mydailyfisiapp.data.repository.interfaces

import com.pruebita.mydailyfisiapp.data.model.domain.Event

interface EventRepository {
    fun listAllTodayEvents():MutableList<Event>
}