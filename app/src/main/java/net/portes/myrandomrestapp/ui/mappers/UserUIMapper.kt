package net.portes.myrandomrestapp.ui.mappers

import net.portes.myrandomrestapp.ui.models.UserUI
import net.portes.shared.data.BothBaseMapper
import net.portes.users.domain.models.UserDto
import javax.inject.Inject

/**
 * @author amadeus.portes
 */
class UserUIMapper @Inject constructor(private val locationUIMapper: LocationUIMapper) :
    BothBaseMapper<UserDto, UserUI> {
    override fun mapFrom(from: UserDto): UserUI = UserUI(
        id = from.id,
        cell = from.cell,
        email = from.email,
        gender = from.gender,
        address = from.address,
        location = locationUIMapper.mapFrom(from.location),
        name = from.name,
        nat = from.nat,
        phone = from.phone,
        picture = from.picture
    )

    override fun mapTo(from: UserUI): UserDto = UserDto(
        id = from.id,
        cell = from.cell,
        email = from.email,
        gender = from.gender,
        address = from.address,
        location = locationUIMapper.mapTo(from.location),
        name = from.name,
        nat = from.nat,
        phone = from.phone,
        picture = from.picture
    )
}