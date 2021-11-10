package net.portes.users.data.mappers

import net.portes.shared.data.BaseDefaultMapper
import net.portes.shared.data.BaseMapper
import net.portes.users.data.models.LocationEntity
import net.portes.users.data.models.UserResponse
import net.portes.users.domain.models.UserDto
import javax.inject.Inject

/**
 * @author amadeus.portes
 */
class UsersMapper @Inject constructor(private val locationMapper: LocationMapper) :
    BaseMapper<UserResponse, UserDto>, BaseDefaultMapper<UserResponse> {
    override fun mapFrom(from: UserResponse): UserDto {
        return if (from.results.isNotEmpty()) {
            UserDto(
                cell = from.results.first().cell,
                email = from.results.first().email,
                gender = from.results.first().gender,
                address = getAddress(from.results.first().location),
                location = locationMapper.mapFrom(from.results.first().location),
                name = "${from.results.first().name.title} ${from.results.first().name.first} ${from.results.first().name.last}",
                nat = from.results.first().nat,
                phone = from.results.first().phone,
                picture = from.results.first().picture.large
            )
        } else {
            UserDto()
        }
    }

    override fun toDefault(): UserResponse = UserResponse(listOf())

    private fun getAddress(location: LocationEntity) =
        "${location.street.name} #${location.street.number},  ${location.city} ${location.postcode} ${location.state}, ${location.country}"
}