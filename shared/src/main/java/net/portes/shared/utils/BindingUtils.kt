package net.portes.shared.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import net.portes.shared.R

/**
 * @author amadeus.portes
 */
@BindingAdapter("roundImage")
fun roundImage(view: ImageView, url: String?) {
    Picasso.get()
        .load(url)
        .placeholder(R.drawable.ic_account)
        .fit()
        .centerCrop()
        .into(view, RoundImage(view, view.context))
}
