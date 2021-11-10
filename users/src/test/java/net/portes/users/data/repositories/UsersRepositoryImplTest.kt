package net.portes.users.data.repositories

import net.portes.shared.models.Either
import net.portes.shared.models.Failure
import net.portes.users.domain.datasources.UsersDataSource
import net.portes.users.domain.models.UserDto
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author amadeus.portes
 */
@RunWith(MockitoJUnitRunner.StrictStubs::class)
class UsersRepositoryImplTest {

    @Mock
    private lateinit var dataSource: UsersDataSource

    private val repository by lazy {
        UsersRepositoryImpl(dataSource)
    }

    @Test
    fun `validate repository get users success`() {
        // Given
        `when`(dataSource.getUsers()).thenReturn(Either.Right(UserDto()))

        // When
        val data = repository.getUsers()

        // Then
        assertEquals(true, data is Either.Right)
    }

    @Test
    fun `validate repository get users failed`() {
        // Given
        `when`(dataSource.getUsers()).thenReturn(Either.Left(Failure.ServerError))

        // When
        val data = repository.getUsers()

        // Then
        assertEquals(true, data is Either.Left)
    }


}