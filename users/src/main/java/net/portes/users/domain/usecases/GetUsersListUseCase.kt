package net.portes.users.domain.usecases

import kotlinx.coroutines.CoroutineDispatcher
import net.portes.shared.di.IoDispatcher
import net.portes.shared.domain.UseCase
import net.portes.shared.models.Either
import net.portes.shared.models.Failure
import net.portes.users.domain.models.UserDto
import net.portes.users.domain.repositories.UsersRepository
import javax.inject.Inject

/**
 * @author amadeus.portes
 */
class GetUsersListUseCase @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val repository: UsersRepository
) : UseCase<Unit, UserDto>(dispatcher) {
    override suspend fun execute(params: Unit): Either<Failure, UserDto> =
        repository.getUsers()
}