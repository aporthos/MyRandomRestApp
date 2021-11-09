package net.portes.users.domain.models

/**
 * @author amadeus.portes
 */
data class LocationDto(
    val city: String,
    val coordinates: CoordinatesDto,
    val country: String,
    val postcode: Int,
    val state: String,
    val street: StreetDto
)