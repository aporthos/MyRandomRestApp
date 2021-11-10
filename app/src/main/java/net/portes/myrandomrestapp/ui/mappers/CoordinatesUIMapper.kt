package net.portes.myrandomrestapp.ui.mappers

import net.portes.myrandomrestapp.ui.models.CoordinatesUI
import net.portes.shared.data.BothBaseMapper
import net.portes.shared.extensions.value
import net.portes.users.domain.models.CoordinatesDto
import javax.inject.Inject

/**
 * @author amadeus.portes
 */
class CoordinatesUIMapper @Inject constructor(): BothBaseMapper<CoordinatesDto?, CoordinatesUI> {
    override fun mapFrom(from: CoordinatesDto?): CoordinatesUI = CoordinatesUI(
        latitude = from?.latitude.value(),
        longitude = from?.longitude.value()
    )

    override fun mapTo(from: CoordinatesUI): CoordinatesDto = CoordinatesDto(
        latitude = from.latitude.value(),
        longitude = from.longitude.value()
    )
}