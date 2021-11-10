package net.portes.myrandomrestapp.ui.mappers

import net.portes.myrandomrestapp.ui.models.StreetUI
import net.portes.shared.data.BothBaseMapper
import net.portes.shared.extensions.value
import net.portes.users.domain.models.StreetDto
import javax.inject.Inject

/**
 * @author amadeus.portes
 */
class StreetUIMapper @Inject constructor() : BothBaseMapper<StreetDto?, StreetUI> {
    override fun mapFrom(from: StreetDto?): StreetUI = StreetUI(
        name = from?.name.value(),
        number = from?.number ?: 0
    )

    override fun mapTo(from: StreetUI): StreetDto = StreetDto(
        name = from.name.value(),
        number = from.number ?: 0
    )
}