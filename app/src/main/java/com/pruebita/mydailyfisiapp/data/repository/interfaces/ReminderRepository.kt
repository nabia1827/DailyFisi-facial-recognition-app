package com.pruebita.mydailyfisiapp.data.repository.interfaces

import com.pruebita.mydailyfisiapp.data.model.domain.Reminder
import kotlinx.datetime.LocalDate

interface ReminderRepository {
    fun listAllRemindersEvents(timeWeek: LocalDate): MutableList<Reminder>
}