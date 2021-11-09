package net.portes.users.data.services

import net.portes.users.data.models.UserResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * @author amadeus.portes
 */
interface UserService {
    @GET("api")
    fun users(): Call<UserResponse>
}