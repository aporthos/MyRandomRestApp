package net.portes.users.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @author amadeus.portes
 */
@JsonClass(generateAdapter = true)
data class StreetEntity(
    @Json(name = "name")
    val name: String,
    @Json(name = "number")
    val number: Int
)