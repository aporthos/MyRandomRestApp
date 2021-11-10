package net.portes.myrandomrestapp.ui.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @author amadeus.portes
 */
@Parcelize
data class LocationUI(
    val city: String,
    val coordinates: CoordinatesUI,
    val country: String,
    val postcode: Int,
    val state: String,
    val street: StreetUI
) : Parcelable