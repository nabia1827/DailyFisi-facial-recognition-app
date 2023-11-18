package com.pruebita.mydailyfisiapp.data.repository.interfaces

import com.pruebita.mydailyfisiapp.data.model.domain.Reminder
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

interface ReminderRepository {
    fun listAllRemindersEvents(timeWeek: LocalDate): MutableList<Reminder>

    fun markAsCompleted(idReminder: Int, idUser: Int?)

    fun addReminder(userId: Int?, title: String, startTime: LocalDateTime, endTime: LocalDateTime): Int

    fun editReminder(userId: Int?, idReminder: Int, title: String, startTime: LocalDateTime, endTime: LocalDateTime)
}