package net.portes.users.domain.models

/**
 * @author amadeus.portes
 */
data class UserDto(
    var id: String = "",
    val cell: String = "",
    val email: String = "",
    val gender: String = "",
    val address: String = "",
    val location: LocationDto? = null,
    val name: String = "",
    val nat: String = "",
    val phone: String = "",
    val picture: String = ""
)