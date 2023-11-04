package com.pruebita.mydailyfisiapp.data.repository.repositories

import com.pruebita.mydailyfisiapp.data.model.Event
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
                eventImage = "https://img2.storyblok.com/f/83829/1200x628/96185170bd/esperance-vie-akita-inu.jpg",
                eventDate = Calendar.getInstance(),
                place = "Auditorio FISI",
                address = "Av. Perez Tudela",
                publisherName = "Lucia Rivera",
                publisherImage = "https://img2.storyblok.com/f/83829/1200x628/96185170bd/esperance-vie-akita-inu.jpg",
                category = "Estudios",
                publishingDate = Calendar.getInstance(),
            ),
            Event(
                idEvent = 1,
                idUser = 1,
                title = "Aniversario",
                shortDescription = "El día de hoy se presentaran 5 grupos en el Auditorio.",
                longDescription = "El día de hoy se presentaran 5 grupos en el Auditorio.",
                eventImage = "https://img2.storyblok.com/f/83829/1200x628/96185170bd/esperance-vie-akita-inu.jpg",
                eventDate = Calendar.getInstance(),
                place = "Auditorio FISI",
                address = "Av. Perez Tudela",
                publisherName = "Lucia Rivera",
                publisherImage = "https://img2.storyblok.com/f/83829/1200x628/96185170bd/esperance-vie-akita-inu.jpg",
                category = "Estudios",
                publishingDate = Calendar.getInstance(),
            ),
            Event(
                idEvent = 1,
                idUser = 1,
                title = "WIE UNMSM",
                shortDescription = "El día de hoy se presentaran 5 grupos en el Auditorio.",
                longDescription = "El día de hoy se presentaran 5 grupos en el Auditorio.",
                eventImage = "https://img2.storyblok.com/f/83829/1200x628/96185170bd/esperance-vie-akita-inu.jpg",
                eventDate = Calendar.getInstance(),
                place = "Auditorio FISI",
                address = "Av. Perez Tudela",
                publisherName = "Lucia Rivera",
                publisherImage = "https://img2.storyblok.com/f/83829/1200x628/96185170bd/esperance-vie-akita-inu.jpg",
                category = "Estudios",
                publishingDate = Calendar.getInstance(),
            ),
            Event(
                idEvent = 1,
                idUser = 1,
                title = "Hult Prize",
                shortDescription = "El día de hoy se presentaran 5 grupos en el Auditorio.",
                longDescription = "El día de hoy se presentaran 5 grupos en el Auditorio.",
                eventImage = "https://img2.storyblok.com/f/83829/1200x628/96185170bd/esperance-vie-akita-inu.jpg",
                eventDate = Calendar.getInstance(),
                place = "Auditorio FISI",
                address = "Av. Perez Tudela",
                publisherName = "Lucia Rivera",
                publisherImage = "https://img2.storyblok.com/f/83829/1200x628/96185170bd/esperance-vie-akita-inu.jpg",
                category = "Estudios",
                publishingDate = Calendar.getInstance(),
            ),
            Event(
                idEvent = 1,
                idUser = 1,
                title = "Charla NTT DATA",
                shortDescription = "El día de hoy se presentaran 5 grupos en el Auditorio.",
                longDescription = "El día de hoy se presentaran 5 grupos en el Auditorio.",
                eventImage = "https://img2.storyblok.com/f/83829/1200x628/96185170bd/esperance-vie-akita-inu.jpg",
                eventDate = Calendar.getInstance(),
                place = "Auditorio FISI",
                address = "Av. Perez Tudela",
                publisherName = "Lucia Rivera",
                publisherImage = "https://img2.storyblok.com/f/83829/1200x628/96185170bd/esperance-vie-akita-inu.jpg",
                category = "Estudios",
                publishingDate = Calendar.getInstance(),
            ))
    }
}