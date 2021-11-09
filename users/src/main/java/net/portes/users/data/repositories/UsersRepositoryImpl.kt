package net.portes.users.data.repositories

import net.portes.shared.models.Either
import net.portes.shared.models.Failure
import net.portes.users.domain.datasources.UsersDataSource
import net.portes.users.domain.models.UserDto
import net.portes.users.domain.repositories.UsersRepository
import javax.inject.Inject

/**
 * @author amadeus.portes
 */
class UsersRepositoryImpl @Inject constructor(private val dataSource: UsersDataSource) :
    UsersRepository {
    override fun getUsers(): Either<Failure, UserDto> = dataSource.getUsers()
}