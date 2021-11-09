package net.portes.shared.utils

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView
import androidx.core.graphics.drawable.RoundedBitmapDrawable
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import net.portes.shared.R
import com.squareup.picasso.Callback
import kotlin.math.max

/**
 * @author amadeus.portes
 */
class RoundImage(private val imageView: ImageView, private val context: Context) : Callback {
    override fun onSuccess() {
        val imageBitmap = (imageView.drawable as BitmapDrawable).bitmap
        val imageDrawable: RoundedBitmapDrawable =
            RoundedBitmapDrawableFactory.create(context.resources, imageBitmap)
        imageDrawable.isCircular = true
        imageDrawable.cornerRadius = max(imageBitmap.width.toFloat(), imageBitmap.height / 2.0f)
        imageView.setImageDrawable(imageDrawable)
    }

    override fun onError(e: Exception?) {
        imageView.setImageResource(R.drawable.ic_account)
    }

}