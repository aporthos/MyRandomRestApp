package net.portes.users.data.mappers

import net.portes.shared.data.BaseMapper
import net.portes.users.data.models.LocationEntity
import net.portes.users.data.models.UserEntity
import net.portes.users.domain.models.UserDto
import javax.inject.Inject

/**
 * @author amadeus.portes
 */
class UsersMapper @Inject constructor(
    private val locationMapper: LocationMapper,
    private val pictureMapper: PictureMapper
) :
    BaseMapper<UserEntity, UserDto> {
    override fun mapFrom(from: UserEntity): UserDto = UserDto(
        cell = from.cell,
        email = from.email,
        gender = from.gender,
        address = getAddress(from.location),
        location = locationMapper.mapFrom(from.location),
        name = "${from.name.title} ${from.name.first} ${from.name.last}",
        nat = from.nat,
        phone = from.phone,
        picture = pictureMapper.mapFrom(from.picture)
    )

    private fun getAddress(location: LocationEntity) =
        "${location.street.name} #${location.street.number},  ${location.city} ${location.postcode} ${location.state}, ${location.country}"

}
