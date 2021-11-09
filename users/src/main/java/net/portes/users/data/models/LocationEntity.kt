package net.portes.users.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @author amadeus.portes
 */
@JsonClass(generateAdapter = true)
data class LocationEntity(
    @Json(name = "city")
    val city: String,
    @Json(name = "coordinates")
    val coordinates: CoordinatesEntity,
    @Json(name = "country")
    val country: String,
    @Json(name = "postcode")
    val postcode: Int,
    @Json(name = "state")
    val state: String,
    @Json(name = "street")
    val street: StreetEntity
)