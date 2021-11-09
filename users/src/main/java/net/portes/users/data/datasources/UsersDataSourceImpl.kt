package net.portes.users.data.datasources

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import net.portes.shared.extensions.call
import net.portes.shared.models.Either
import net.portes.shared.models.Failure
import net.portes.shared.utils.NetworkHandler
import net.portes.users.data.mappers.UsersListMapper
import net.portes.users.data.services.UserService
import net.portes.users.domain.datasources.UsersDataSource
import net.portes.users.domain.models.UserDto
import timber.log.Timber
import javax.inject.Inject

/**
 * @author amadeus.portes
 */
class UsersDataSourceImpl @Inject constructor(
    private val service: UserService,
    private val networkHandler: NetworkHandler,
    private val mapper: UsersListMapper,
    private val firestore: FirebaseFirestore
) : UsersDataSource {

    companion object {
        private const val COLLECTION_USERS = "users"
    }

    override fun getUsers(): Either<Failure, UserDto> {
        return when (networkHandler.isNetworkAvailable()) {
            true -> service.users().call({ mapper.mapFrom(it) }, mapper.toDefault())
            false -> Either.Left(Failure.NetworkConnection)
        }
    }

    override suspend fun saveUser(user: UserDto): Either<Failure, Boolean> {
        return when (networkHandler.isNetworkAvailable()) {
            true -> toSaveUser(user)
            false -> Either.Left(Failure.NetworkConnection)
        }
    }

    override suspend fun deleteUser(id: String): Either<Failure, Boolean> {
        return try {
            firestore.collection(COLLECTION_USERS).document(id).delete()
            Either.Right(true)
        } catch (e: Exception) {
            Timber.i("toSaveUser: -> $e")
            Either.Left(Failure.ServerError)
        }
    }

    private suspend fun toSaveUser(user: UserDto): Either<Failure, Boolean> {
        return try {
            val document = firestore.collection(COLLECTION_USERS).document()
            val id = document.id
            user.id = id
            document.set(user).await()
            Either.Right(true)
        } catch (e: Exception) {
            Timber.i("toSaveUser: -> $e")
            Either.Left(Failure.ServerError)
        }
    }
}