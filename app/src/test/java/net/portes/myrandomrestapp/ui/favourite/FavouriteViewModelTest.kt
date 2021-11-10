package net.portes.myrandomrestapp.ui.favourite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import net.portes.myrandomrestapp.MainCoroutineRule
import net.portes.myrandomrestapp.runBlockingTest
import net.portes.shared.extensions.getThisValue
import net.portes.shared.models.Either
import net.portes.shared.models.Failure
import net.portes.shared.ui.base.ViewState
import net.portes.users.domain.usecases.DeleteUserUseCase
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author amadeus.portes
 */
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner.StrictStubs::class)
class FavouriteViewModelTest {

    @Mock
    private lateinit var deleteUserUseCase: DeleteUserUseCase

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantRule = InstantTaskExecutorRule()

    private val viewModel by lazy {
        FavouriteViewModel(deleteUserUseCase)
    }

    @Test
    fun `validate delete user success`() = mainCoroutineRule.runBlockingTest {
        // Given
        mainCoroutineRule.testDispatcher.pauseDispatcher()
        Mockito.`when`(deleteUserUseCase("12345")).thenReturn(Either.Right(true))

        // When
        viewModel.deleteUser("12345")

        // Then
        Assert.assertTrue(viewModel.deleteUser.getThisValue() is ViewState.Loading)
        mainCoroutineRule.testDispatcher.resumeDispatcher()
        Assert.assertTrue(viewModel.deleteUser.getThisValue() is ViewState.Success)
    }

    @Test
    fun `validate delete user failed`() = mainCoroutineRule.runBlockingTest {
        // Given
        mainCoroutineRule.testDispatcher.pauseDispatcher()
        Mockito.`when`(deleteUserUseCase("12345")).thenReturn(Either.Left(Failure.ServerError))

        // When
        viewModel.deleteUser("12345")

        // Then
        Assert.assertTrue(viewModel.deleteUser.getThisValue() is ViewState.Loading)
        mainCoroutineRule.testDispatcher.resumeDispatcher()
        Assert.assertTrue(viewModel.deleteUser.getThisValue() is ViewState.Error)
    }

}