package com.pruebita.mydailyfisiapp.data.model.domain

import kotlinx.datetime.LocalDateTime

data class Reminder(
    val idReminder: Int = -1,
    val title: String = "",
    val dateStart: LocalDateTime,
    val dateEnd: LocalDateTime,
    val isDone: Boolean
)