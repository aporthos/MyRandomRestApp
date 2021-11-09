package net.portes.users.domain.models

/**
 * @author amadeus.portes
 */
data class LocationDto(
    val city: String = "",
    val coordinates: CoordinatesDto? = null,
    val country: String = "",
    val postcode: Int = 0,
    val state: String = "",
    val street: StreetDto? = null
)