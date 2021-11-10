package net.portes.myrandomrestapp.ui.users

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import net.portes.myrandomrestapp.MainCoroutineRule
import net.portes.myrandomrestapp.MockFactory.getUser
import net.portes.myrandomrestapp.MockFactory.getUserUI
import net.portes.myrandomrestapp.runBlockingTest
import net.portes.myrandomrestapp.ui.mappers.CoordinatesUIMapper
import net.portes.myrandomrestapp.ui.mappers.LocationUIMapper
import net.portes.myrandomrestapp.ui.mappers.StreetUIMapper
import net.portes.myrandomrestapp.ui.mappers.UserUIMapper
import net.portes.shared.extensions.getThisValue
import net.portes.shared.models.Either
import net.portes.shared.models.Failure
import net.portes.shared.ui.base.ViewState
import net.portes.users.domain.usecases.GetUsersListUseCase
import net.portes.users.domain.usecases.SaveUserUseCase
import org.junit.Assert.*
import org.junit.Rule
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
class UsersViewModelTest {

    @Mock
    private lateinit var getUsersListUseCase: GetUsersListUseCase

    @Mock
    private lateinit var saveUserUseCase: SaveUserUseCase

    private val userUIMapper by lazy {
        UserUIMapper(LocationUIMapper(CoordinatesUIMapper(), StreetUIMapper()))
    }

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantRule = InstantTaskExecutorRule()

    private val viewModel by lazy {
        UsersViewModel(getUsersListUseCase, saveUserUseCase, userUIMapper)
    }

    @Test
    fun `validate get user list success`() = mainCoroutineRule.runBlockingTest {
        // Given
        mainCoroutineRule.testDispatcher.pauseDispatcher()
        `when`(getUsersListUseCase(Unit)).thenReturn(Either.Right(getUser()))

        // When
        viewModel.getUsersList()

        // Then
        assertTrue(viewModel.usersList.getThisValue() is ViewState.Loading)
        mainCoroutineRule.testDispatcher.resumeDispatcher()
        assertTrue(viewModel.usersList.getThisValue() is ViewState.Success)
    }

    @Test
    fun `validate get user list failed`() = mainCoroutineRule.runBlockingTest {
        // Given
        mainCoroutineRule.testDispatcher.pauseDispatcher()
        `when`(getUsersListUseCase(Unit)).thenReturn(Either.Left(Failure.ServerError))

        // When
        viewModel.getUsersList()

        // Then
        assertTrue(viewModel.usersList.getThisValue() is ViewState.Loading)
        mainCoroutineRule.testDispatcher.resumeDispatcher()
        assertTrue(viewModel.usersList.getThisValue() is ViewState.Error)
    }

    @Test
    fun `validate save user success`() = mainCoroutineRule.runBlockingTest {
        // Given
        mainCoroutineRule.testDispatcher.pauseDispatcher()
        `when`(saveUserUseCase(getUser())).thenReturn(Either.Right(true))

        // When
        viewModel.saveUser(getUserUI())

        // Then
        assertTrue(viewModel.saveUser.getThisValue() is ViewState.Loading)
        mainCoroutineRule.testDispatcher.resumeDispatcher()
        assertTrue(viewModel.saveUser.getThisValue() is ViewState.Success)
    }

    @Test
    fun `validate save user failed`() = mainCoroutineRule.runBlockingTest {
        // Given
        mainCoroutineRule.testDispatcher.pauseDispatcher()
        `when`(saveUserUseCase(getUser())).thenReturn(Either.Left(Failure.ServerError))

        // When
        viewModel.saveUser(getUserUI())

        // Then
        assertTrue(viewModel.saveUser.getThisValue() is ViewState.Loading)
        mainCoroutineRule.testDispatcher.resumeDispatcher()
        assertTrue(viewModel.saveUser.getThisValue() is ViewState.Error)
    }

}