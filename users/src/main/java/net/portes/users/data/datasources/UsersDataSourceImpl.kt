package net.portes.users.data.datasources

import net.portes.shared.extensions.call
import net.portes.shared.models.Either
import net.portes.shared.models.Failure
import net.portes.shared.utils.NetworkHandler
import net.portes.users.data.mappers.UsersListMapper
import net.portes.users.data.services.UserService
import net.portes.users.domain.datasources.UsersDataSource
import net.portes.users.domain.models.UserDto
import javax.inject.Inject

/**
 * @author amadeus.portes
 */
class UsersDataSourceImpl @Inject constructor(
    private val service: UserService,
    private val networkHandler: NetworkHandler,
    private val mapper: UsersListMapper

) : UsersDataSource {
    override fun getUsers(): Either<Failure, UserDto> {
        return when (networkHandler.isNetworkAvailable()) {
            true -> service.users().call({ mapper.mapFrom(it) }, mapper.toDefault())
            false -> Either.Left(Failure.NetworkConnection)
        }
    }
}