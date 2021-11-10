package net.portes.users.data.datasources

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import net.portes.shared.models.Either
import net.portes.shared.utils.NetworkHandler
import net.portes.users.data.mappers.CoordinatesMapper
import net.portes.users.data.mappers.LocationMapper
import net.portes.users.data.mappers.StreetMapper
import net.portes.users.data.mappers.UsersMapper
import net.portes.users.data.models.UserResponse
import net.portes.users.data.services.UserService
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Response
import retrofit2.Response.error

/**
 * @author amadeus.portes
 */
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner.StrictStubs::class)
class UsersDataSourceImplTest {

    @Mock
    private lateinit var service: UserService

    @Mock
    private lateinit var networkHandler: NetworkHandler

    @Mock
    private lateinit var firestore: FirebaseFirestore

    @Mock
    private lateinit var call: Call<UserResponse>

    private val mapper by lazy {
        UsersMapper(LocationMapper(CoordinatesMapper(), StreetMapper()))
    }

    private val dataSource by lazy {
        UsersDataSourceImpl(service, networkHandler, mapper, firestore)
    }

    @Test
    fun `validate data source get users success`() {
        // Given
        `when`(call.execute()).thenReturn(Response.success(UserResponse(emptyList())))
        `when`(service.users()).thenReturn(call)
        `when`(networkHandler.isNetworkAvailable()).thenReturn(true)

        // When
        val data = dataSource.getUsers()

        // Then
        assertEquals(true, data is Either.Right)
    }

    @Test
    fun `validate data source get users failed`() {
        // Given
        `when`(call.execute()).thenReturn(error(401, anyError()))
        `when`(service.users()).thenReturn(call)
        `when`(networkHandler.isNetworkAvailable()).thenReturn(true)

        // When
        val data = dataSource.getUsers()

        // Then
        assertEquals(true, data is Either.Left)
    }

    fun anyError(): ResponseBody = ResponseBody.create(
        MediaType.parse("application/json"), """
                {
                    "code": "InvalidCredentials",
                    "message": "That hash, timestamp and key combination is invalid."
                }
            """.trimIndent()

    )


}