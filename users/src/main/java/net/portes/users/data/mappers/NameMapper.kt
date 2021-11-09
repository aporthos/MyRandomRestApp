package net.portes.users.data.mappers

import net.portes.shared.data.BaseMapper
import net.portes.users.data.models.NameEntity
import net.portes.users.domain.models.NameDto
import javax.inject.Inject

/**
 * @author amadeus.portes
 */
class NameMapper @Inject constructor() : BaseMapper<NameEntity, NameDto> {
    override fun mapFrom(from: NameEntity): NameDto = NameDto(
        first = from.first,
        last = from.last,
        title = from.title
    )
}