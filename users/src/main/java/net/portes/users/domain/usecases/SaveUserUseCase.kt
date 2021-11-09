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
class SaveUserUseCase @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val repository: UsersRepository
) : UseCase<UserDto, Boolean>(dispatcher) {
    override suspend fun execute(params: UserDto): Either<Failure, Boolean> =
        repository.saveUser(params)
}