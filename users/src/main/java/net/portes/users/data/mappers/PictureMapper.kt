package net.portes.users.data.mappers

import net.portes.shared.data.BaseMapper
import net.portes.users.data.models.PictureEntity
import net.portes.users.domain.models.PictureDto
import javax.inject.Inject

/**
 * @author amadeus.portes
 */
class PictureMapper @Inject constructor() : BaseMapper<PictureEntity, PictureDto> {
    override fun mapFrom(from: PictureEntity): PictureDto = PictureDto(
        large = from.large,
        medium = from.medium,
        thumbnail = from.thumbnail
    )
}