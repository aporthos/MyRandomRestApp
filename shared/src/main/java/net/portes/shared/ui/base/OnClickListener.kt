package net.portes.shared.ui.base

import android.view.View

/**
 * @author amadeus.portes
 */
interface OnClickListener<T> {
    fun onClicked(v: View, item: T)
}