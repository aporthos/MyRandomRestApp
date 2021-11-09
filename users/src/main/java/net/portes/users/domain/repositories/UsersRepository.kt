package net.portes.users.domain.repositories

import net.portes.shared.models.Either
import net.portes.shared.models.Failure
import net.portes.users.domain.models.UserDto

/**
 * @author amadeus.portes
 */
interface UsersRepository {
    fun getUsers(): Either<Failure, UserDto>
}