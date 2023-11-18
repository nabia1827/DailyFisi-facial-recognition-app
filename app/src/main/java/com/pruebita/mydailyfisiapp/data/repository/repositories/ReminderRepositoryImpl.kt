package com.pruebita.mydailyfisiapp.data.repository.repositories

import com.pruebita.mydailyfisiapp.data.model.domain.Reminder
import com.pruebita.mydailyfisiapp.data.repository.interfaces.ReminderRepository
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

class ReminderRepositoryImpl: ReminderRepository{

    override fun listAllRemindersEvents(timeWeek: LocalDate): MutableList<Reminder>{
        return mutableListOf(
            Reminder(
                idReminder = 1,
                title = "Reunion de Gestion de Proyectos",
                dateStart = LocalDateTime(2023, 11, 16, 12, 30),
                dateEnd = LocalDateTime(2023, 11, 16, 12, 30),
                isDone = false
            ),
            Reminder(
                idReminder = 2,
                title = "Reunion de Calculo",
                dateStart = LocalDateTime(2023, 11, 16, 12, 30),
                dateEnd = LocalDateTime(2023, 11, 16, 12, 30),
                isDone = true
            ),
            Reminder(
                idReminder = 3,
                title = "Reunion de Algebra",
                dateStart = LocalDateTime(2023, 11, 16, 12, 30),
                dateEnd = LocalDateTime(2023, 11, 16, 12, 30),
                isDone = true
            )
        )
    }

    override fun markAsCompleted(idReminder: Int, idUser: Int?) {
        //Update the Reminder as Completed
    }

    override fun addReminder(userId: Int?, title: String, startTime: LocalDateTime, endTime: LocalDateTime): Int {
        //Update and return the id value
        return 4
    }

    override  fun editReminder(
        userId: Int?,
        idReminder: Int,
        title: String,
        startTime: LocalDateTime,
        endTime: LocalDateTime
    ){
        //Edit the Reminder by its userId
    }



}