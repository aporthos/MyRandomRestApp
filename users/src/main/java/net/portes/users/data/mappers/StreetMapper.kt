package net.portes.users.data.mappers

import net.portes.shared.data.BaseMapper
import net.portes.users.data.models.StreetEntity
import net.portes.users.domain.models.StreetDto
import javax.inject.Inject

/**
 * @author amadeus.portes
 */
class StreetMapper @Inject constructor() : BaseMapper<StreetEntity, StreetDto> {
    override fun mapFrom(from: StreetEntity): StreetDto = StreetDto(
        name = from.name,
        number = from.number
    )
}