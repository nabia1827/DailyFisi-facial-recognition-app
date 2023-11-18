package com.pruebita.mydailyfisiapp.data.model.domain

import kotlinx.datetime.LocalDateTime

data class Reminder(
    val idReminder: Int = -1,
    var title: String = "",
    var dateStart: LocalDateTime,
    var dateEnd: LocalDateTime,
    var isDone: Boolean
)