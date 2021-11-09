package net.portes.users.data.mappers

import net.portes.shared.data.BaseDefaultMapper
import net.portes.shared.data.BaseMapper
import net.portes.users.data.models.UserEntity
import net.portes.users.data.models.UserResponse
import net.portes.users.domain.models.UserDto
import javax.inject.Inject

/**
 * @author amadeus.portes
 */
class UsersListMapper @Inject constructor(private val mapper: UsersMapper) :
    BaseMapper<UserResponse, UserDto>, BaseDefaultMapper<UserResponse> {
    override fun mapFrom(from: UserResponse): UserDto {
        return if (from.results.isNotEmpty()) {
            mapper.mapFrom(from.results.first())
        } else {
            mapper.mapFrom(emptyList<UserEntity>().first())
        }
    }

    override fun toDefault(): UserResponse = UserResponse(listOf())
}