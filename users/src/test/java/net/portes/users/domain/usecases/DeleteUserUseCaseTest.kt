package net.portes.users.domain.usecases

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import net.portes.shared.models.Either
import net.portes.shared.models.Failure
import net.portes.users.domain.repositories.UsersRepository
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


/**
 * @author amadeus.portes
 */
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner.StrictStubs::class)
class DeleteUserUseCaseTest {

    @Mock
    private lateinit var repository: UsersRepository

    private val useCase by lazy {
        DeleteUserUseCase(TestCoroutineDispatcher(), repository)
    }

    @Test
    fun `validation use case success`() = runBlockingTest {
        `when`(repository.deleteUser("")).thenReturn(Either.Right(true))

        val useCase = useCase("")
        assertEquals(true, useCase is Either.Right)
    }

    @Test
    fun `validation use case failed`() = runBlockingTest {
        `when`(repository.deleteUser("")).thenReturn(Either.Left(Failure.ServerError))

        val useCase = useCase("")
        assertEquals(true, useCase is Either.Left)
    }

}