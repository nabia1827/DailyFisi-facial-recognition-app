package com.pruebita.mydailyfisiapp.data.repository.repositories

import com.pruebita.mydailyfisiapp.data.model.domain.Reminder
import com.pruebita.mydailyfisiapp.data.repository.interfaces.ReminderRepository
import com.pruebita.mydailyfisiapp.data.source.myRemainders
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

class ReminderRepositoryImpl: ReminderRepository{

    override fun listAllRemindersEvents(timeWeek: LocalDate): MutableList<Reminder>{

        var currentRemainders: MutableList<Reminder> = mutableListOf()
        for (i in 0 until myRemainders.size){
            if(timeWeek.year == myRemainders[i].dateStart.year
                && timeWeek.month == myRemainders[i].dateStart.month
                && timeWeek.dayOfMonth == myRemainders[i].dateStart.dayOfMonth){
                currentRemainders.add(myRemainders[i])
            }
        }
        return currentRemainders
    }

    override fun markAsCompleted(idReminder: Int, idUser: Int?) {
        for (i in 0 until myRemainders.size){
            if(myRemainders[i].idReminder == idReminder){
                myRemainders[i].isDone = true
            }
        }
    }

    override fun addReminder(userId: Int?, title: String, startTime: LocalDateTime, endTime: LocalDateTime): Int {
        val id=myRemainders.size
        myRemainders.add(
            Reminder(
                idReminder = id,
                title = title,
                dateStart = startTime,
                dateEnd = endTime,
                isDone = false
            )
        )
        println("Titulooo R: ${myRemainders[myRemainders.size-1].title}")
        return id
    }

    override  fun editReminder(
        userId: Int?,
        idReminder: Int,
        title: String,
        startTime: LocalDateTime,
        endTime: LocalDateTime
    ){
        for (i in 0 until myRemainders.size){
            if(myRemainders[i].idReminder == idReminder){
                myRemainders[i].dateStart = startTime
                myRemainders[i].dateEnd = endTime
                myRemainders[i].title = title
            }
        }
    }



}