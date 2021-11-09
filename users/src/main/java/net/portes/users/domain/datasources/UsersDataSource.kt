package net.portes.users.domain.datasources

import net.portes.shared.models.Either
import net.portes.shared.models.Failure
import net.portes.users.domain.models.UserDto

/**
 * @author amadeus.portes
 */
interface UsersDataSource {
    fun getUsers(): Either<Failure, UserDto>
    suspend fun saveUser(user: UserDto): Either<Failure, Boolean>
    suspend fun deleteUser(id: String): Either<Failure, Boolean>
}