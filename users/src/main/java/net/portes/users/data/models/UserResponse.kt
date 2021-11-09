package net.portes.users.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @author amadeus.portes
 */
@JsonClass(generateAdapter = true)
data class UserResponse(
    @Json(name = "results")
    val results: List<UserEntity>
)