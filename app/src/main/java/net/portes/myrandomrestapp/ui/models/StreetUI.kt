package net.portes.myrandomrestapp.ui.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @author amadeus.portes
 */
@Parcelize
data class StreetUI(
    val name: String,
    val number: Int
) : Parcelable