package com.pruebita.mydailyfisiapp.data.model.domain

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import java.util.Calendar

data class Event(
    val idEvent: Int = 0,
    val idUser: Int = 0,
    val title: String = "Titulo",
    val shortDescription: String = "Esta es una descripcion corta",
    val longDescription: String = "Esta es una descripcion larga",
    val eventImage: String = "Event Image",
    val eventDate: Calendar? = null,
    val place: String = "Lugar",
    val address:String = "Direccion",
    val publisherName: String = "Publicador", //Inner Join
    val publisherImage: String = "Image", //Inner Join
    val category: String = "Categoria",
    val publishingDate: Calendar? = null
)
