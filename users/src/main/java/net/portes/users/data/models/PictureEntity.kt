package net.portes.users.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @author amadeus.portes
 */
@JsonClass(generateAdapter = true)
data class PictureEntity(
    @Json(name = "large")
    val large: String,
    @Json(name = "medium")
    val medium: String,
    @Json(name = "thumbnail")
    val thumbnail: String
)