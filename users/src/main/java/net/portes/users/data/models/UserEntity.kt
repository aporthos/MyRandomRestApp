package net.portes.users.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @author amadeus.portes
 */
@JsonClass(generateAdapter = true)
data class UserEntity(
    @Json(name = "cell")
    val cell: String,
    @Json(name = "email")
    val email: String,
    @Json(name = "gender")
    val gender: String,
    @Json(name = "location")
    val location: LocationEntity,
    @Json(name = "name")
    val name: NameEntity,
    @Json(name = "nat")
    val nat: String,
    @Json(name = "phone")
    val phone: String,
    @Json(name = "picture")
    val picture: PictureEntity
)