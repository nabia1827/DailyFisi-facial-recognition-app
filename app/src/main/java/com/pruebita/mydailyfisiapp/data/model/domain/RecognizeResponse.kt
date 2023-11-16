package com.pruebita.mydailyfisiapp.data.model.domain
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecognizeResponse(
    @SerialName("estado") val estado: Boolean
)