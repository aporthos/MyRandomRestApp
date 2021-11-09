package net.portes.users.data.mappers

import net.portes.shared.data.BaseMapper
import net.portes.users.data.models.CoordinatesEntity
import net.portes.users.domain.models.CoordinatesDto
import javax.inject.Inject

/**
 * @author amadeus.portes
 */
class CoordinatesMapper @Inject constructor() : BaseMapper<CoordinatesEntity, CoordinatesDto> {
    override fun mapFrom(from: CoordinatesEntity): CoordinatesDto = CoordinatesDto(
        latitude = from.latitude,
        longitude = from.longitude
    )
}