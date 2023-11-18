package com.pruebita.mydailyfisiapp.data.repository.repositories

import com.pruebita.mydailyfisiapp.data.model.domain.Event
import com.pruebita.mydailyfisiapp.data.repository.interfaces.EventRepository
import java.util.Calendar

class EventRepositoryImpl: EventRepository {
    override fun listAllTodayEvents(): MutableList<Event> {
        return mutableListOf(
            Event(
               idEvent = 1,
                idUser = 1,
                title = "Hackathon",
                shortDescription = "El día de hoy se presentaran 5 grupos en el Auditorio.",
                longDescription = "El día de hoy se presentaran 5 grupos en el Auditorio.",
                eventImage = "https://firebasestorage.googleapis.com/v0/b/dailyfisiapp.appspot.com/o/events%2Fimages_events%2Fevent_1.jpg?alt=media&token=6fed1f03-67f0-4357-a32e-fd4876faa52f",
                eventDate = Calendar.getInstance(),
                place = "Auditorio FISI",
                address = "Av. Perez Tudela",
                publisherName = "Lucia Rivera",
                publisherImage = "https://firebasestorage.googleapis.com/v0/b/dailyfisiapp.appspot.com/o/users%2Fprofiles%2Fuser_1.jpg?alt=media&token=8fa61ee1-f687-4e43-8cab-f799bfd58f36",
                category = "Estudios",
                publishingDate = Calendar.getInstance(),
            ),
            Event(
                idEvent = 1,
                idUser = 1,
                title = "Aniversario",
                shortDescription = "El día de hoy se presentaran 5 grupos en el Auditorio.",
                longDescription = "El día de hoy se presentaran 5 grupos en el Auditorio.",
                eventImage = "https://firebasestorage.googleapis.com/v0/b/dailyfisiapp.appspot.com/o/events%2Fimages_events%2Fevent_1.jpg?alt=media&token=6fed1f03-67f0-4357-a32e-fd4876faa52f",
                eventDate = Calendar.getInstance(),
                place = "Auditorio FISI",
                address = "Av. Perez Tudela",
                publisherName = "Lucia Rivera",
                publisherImage = "https://firebasestorage.googleapis.com/v0/b/dailyfisiapp.appspot.com/o/users%2Fprofiles%2Fuser_1.jpg?alt=media&token=8fa61ee1-f687-4e43-8cab-f799bfd58f36",
                category = "Estudios",
                publishingDate = Calendar.getInstance(),
            ),
            Event(
                idEvent = 1,
                idUser = 1,
                title = "WIE UNMSM",
                shortDescription = "El día de hoy se presentaran 5 grupos en el Auditorio.",
                longDescription = "El día de hoy se presentaran 5 grupos en el Auditorio.",
                eventImage = "https://firebasestorage.googleapis.com/v0/b/dailyfisiapp.appspot.com/o/events%2Fimages_events%2Fevent_1.jpg?alt=media&token=6fed1f03-67f0-4357-a32e-fd4876faa52f",
                eventDate = Calendar.getInstance(),
                place = "Auditorio FISI",
                address = "Av. Perez Tudela",
                publisherName = "Lucia Rivera",
                publisherImage = "https://firebasestorage.googleapis.com/v0/b/dailyfisiapp.appspot.com/o/users%2Fprofiles%2Fuser_1.jpg?alt=media&token=8fa61ee1-f687-4e43-8cab-f799bfd58f36",
                category = "Estudios",
                publishingDate = Calendar.getInstance(),
            ),
            Event(
                idEvent = 1,
                idUser = 1,
                title = "Hult Prize",
                shortDescription = "El día de hoy se presentaran 5 grupos en el Auditorio.",
                longDescription = "El día de hoy se presentaran 5 grupos en el Auditorio.",
                eventImage = "https://firebasestorage.googleapis.com/v0/b/dailyfisiapp.appspot.com/o/events%2Fimages_events%2Fevent_1.jpg?alt=media&token=6fed1f03-67f0-4357-a32e-fd4876faa52f",
                eventDate = Calendar.getInstance(),
                place = "Auditorio FISI",
                address = "Av. Perez Tudela",
                publisherName = "Lucia Rivera",
                publisherImage = "https://firebasestorage.googleapis.com/v0/b/dailyfisiapp.appspot.com/o/users%2Fprofiles%2Fuser_1.jpg?alt=media&token=8fa61ee1-f687-4e43-8cab-f799bfd58f36",
                category = "Estudios",
                publishingDate = Calendar.getInstance(),
            ),
            Event(
                idEvent = 1,
                idUser = 1,
                title = "Charla NTT DATA",
                shortDescription = "El día de hoy se presentaran 5 grupos en el Auditorio.",
                longDescription = "El día de hoy se presentaran 5 grupos en el Auditorio.",
                eventImage = "https://firebasestorage.googleapis.com/v0/b/dailyfisiapp.appspot.com/o/events%2Fimages_events%2Fevent_1.jpg?alt=media&token=6fed1f03-67f0-4357-a32e-fd4876faa52f",
                eventDate = Calendar.getInstance(),
                place = "Auditorio FISI",
                address = "Av. Perez Tudela",
                publisherName = "Lucia Rivera",
                publisherImage = "https://firebasestorage.googleapis.com/v0/b/dailyfisiapp.appspot.com/o/users%2Fprofiles%2Fuser_1.jpg?alt=media&token=8fa61ee1-f687-4e43-8cab-f799bfd58f36",
                category = "Estudios",
                publishingDate = Calendar.getInstance(),
            )
        )
    }
}