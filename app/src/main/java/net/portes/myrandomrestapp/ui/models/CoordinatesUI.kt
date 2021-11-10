package net.portes.myrandomrestapp.ui.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @author amadeus.portes
 */
@Parcelize
data class CoordinatesUI(
    val latitude: String,
    val longitude: String
) : Parcelable