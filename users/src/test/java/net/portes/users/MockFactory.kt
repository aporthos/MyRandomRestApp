package net.portes.users

import net.portes.users.data.models.*

/**
 * @author amadeus.portes
 */
object MockFactory {

    fun getUserResponse() = UserResponse(results = listOf(getUserEntity()))

    fun getUserEntity() = UserEntity(
        cell = "0170-1776917",
        email = "hubertine.hausner@example.com",
        gender = "female",
        location = getLocationEntity(),
        name = getNameEntity(),
        nat = "DE",
        phone = "0718-4389707",
        picture = getPictureEntity()
    )

    fun getPictureEntity() = PictureEntity(large = "url", medium = "url", thumbnail = "url")

    fun getLocationEntity() = LocationEntity(
        city = "Mexico",
        coordinates = getCoordinatesEntity(),
        country = "Mexico",
        postcode = 12345,
        state = "Mexico",
        street = getStreetEntity()
    )

    fun getCoordinatesEntity() = CoordinatesEntity(latitude = "123456", longitude = "12345")

    fun getStreetEntity() = StreetEntity(name = "Alameda", number = 12345)

    fun getNameEntity() = NameEntity(first = "Hubertine", last = "Hausner", title = "Ms")
}