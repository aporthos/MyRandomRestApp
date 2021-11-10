package net.portes.myrandomrestapp

import net.portes.myrandomrestapp.ui.models.CoordinatesUI
import net.portes.myrandomrestapp.ui.models.LocationUI
import net.portes.myrandomrestapp.ui.models.StreetUI
import net.portes.myrandomrestapp.ui.models.UserUI
import net.portes.users.domain.models.CoordinatesDto
import net.portes.users.domain.models.LocationDto
import net.portes.users.domain.models.StreetDto
import net.portes.users.domain.models.UserDto

/**
 * @author amadeus.portes
 */
object MockFactory {
    fun getUser() = UserDto(
        id = "",
        cell = "",
        email = "",
        gender = "",
        address = "",
        location = LocationDto(
            city = "",
            coordinates = CoordinatesDto(latitude = "", longitude = ""),
            country = "",
            postcode = 0,
            state = "",
            street = StreetDto(name = "", number = 0)
        ),
        name = "",
        nat = "",
        phone = "",
        picture = ""
    )

    fun getUserUI() = UserUI(
        id = "", cell = "", email = "", gender = "", address = "", location = LocationUI(
            city = "",
            coordinates = CoordinatesUI(
                latitude = "",
                longitude = ""
            ),
            country = "",
            postcode = 0,
            state = "",
            street = StreetUI(name = "", number = 0)
        ), name = "", nat = "", phone = "", picture = ""
    )
}