package net.portes.users.data.mappers

import net.portes.shared.data.BaseMapper
import net.portes.users.data.models.LocationEntity
import net.portes.users.domain.models.LocationDto
import javax.inject.Inject

/**
 * @author amadeus.portes
 */
class LocationMapper @Inject constructor(
    private val coordinatesMapper: CoordinatesMapper,
    private val streetMapper: StreetMapper
) : BaseMapper<LocationEntity, LocationDto> {
    override fun mapFrom(from: LocationEntity): LocationDto = LocationDto(
        city = from.city,
        coordinates = coordinatesMapper.mapFrom(from.coordinates),
        country = from.country,
        postcode = from.postcode,
        state = from.state,
        street = streetMapper.mapFrom(from.street)
    )
}