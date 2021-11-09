package net.portes.users.domain.usecases

import kotlinx.coroutines.CoroutineDispatcher
import net.portes.shared.di.IoDispatcher
import net.portes.shared.domain.UseCase
import net.portes.shared.models.Either
import net.portes.shared.models.Failure
import net.portes.users.domain.repositories.UsersRepository
import javax.inject.Inject

/**
 * @author amadeus.portes
 */
class DeleteUserUseCase @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val repository: UsersRepository
) : UseCase<String, Boolean>(dispatcher) {
    override suspend fun execute(params: String): Either<Failure, Boolean> =
        repository.deleteUser(params)
}