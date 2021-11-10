package net.portes.myrandomrestapp.ui.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @author amadeus.portes
 */
@Parcelize
data class UserUI(
    var id: String,
    val cell: String,
    val email: String,
    val gender: String,
    val address: String,
    val location: LocationUI,
    val name: String,
    val nat: String,
    val phone: String,
    val picture: String
) : Parcelable