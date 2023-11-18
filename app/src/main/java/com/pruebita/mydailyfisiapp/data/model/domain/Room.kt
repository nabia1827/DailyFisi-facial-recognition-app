package com.pruebita.mydailyfisiapp.data.model.domain

data class Room(
    val idRoom: Int = 101,
    val typeRoom: String = "Salon",
    val pavilion: String = "AP", //AP, NP
    val floor: Int = 1,
    val posX: Int = 100,
    val posY: Int = 100,
)