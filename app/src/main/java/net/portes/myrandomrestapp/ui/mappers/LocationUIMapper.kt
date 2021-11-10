package net.portes.myrandomrestapp.ui.mappers

import net.portes.myrandomrestapp.ui.models.LocationUI
import net.portes.shared.data.BothBaseMapper
import net.portes.shared.extensions.value
import net.portes.users.domain.models.LocationDto
import javax.inject.Inject

/**
 * @author amadeus.portes
 */
class LocationUIMapper @Inject constructor(
    private val coordinatesUIMapper: CoordinatesUIMapper,
    private val streetUIMapper: StreetUIMapper
) :
    BothBaseMapper<LocationDto?, LocationUI> {
    override fun mapFrom(from: LocationDto?): LocationUI = LocationUI(
        city = from?.city.value(),
        coordinates = coordinatesUIMapper.mapFrom(from?.coordinates),
        country = from?.country.value(),
        postcode = from?.postcode ?: 0,
        state = from?.state.value(),
        street = streetUIMapper.mapFrom(from?.street)
    )

    override fun mapTo(from: LocationUI): LocationDto = LocationDto(
        city = from.city.value(),
        coordinates = coordinatesUIMapper.mapTo(from.coordinates),
        country = from.country.value(),
        postcode = from.postcode ?: 0,
        state = from.state.value(),
        street = streetUIMapper.mapTo(from.street)
    )
}