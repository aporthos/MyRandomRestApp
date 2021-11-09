package net.portes.shared.extensions

import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_CALL
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import timber.log.Timber

/**
 * @author amadeus.portes
 */
fun <T : Any, L : LiveData<T>?> LifecycleOwner.observe(liveData: L, body: (T) -> Unit) {
    removeObservers(liveData)
    liveData?.observe(this, Observer(body))
}

fun <T : Any, L : LiveData<T>?> LifecycleOwner.removeObservers(liveData: L) {
    liveData?.removeObservers(this)
}

fun <DB : ViewDataBinding> ViewGroup.binding(view: Int): DB {
    return DataBindingUtil.inflate(
        LayoutInflater.from(context),
        view,
        this,
        false
    )
}

fun Context.makeCall(number: String): Boolean {
    return try {
        val intent = Intent(ACTION_CALL, Uri.parse("tel:$number"))
        startActivity(intent)
        true
    } catch (e: Exception) {
        Timber.e("makeCall: -> $e")
        false
    }
}

fun Context.goLocation(latitude: String, longitude: String): Boolean {
    return try {
        val intent = Intent(ACTION_VIEW, Uri.parse("geo:$latitude,$longitude"))
        startActivity(intent)
        true
    } catch (e: Exception) {
        Timber.e("makeCall: -> $e")
        false
    }
}

fun Context.sms(phone: String?, body: String = "") {
    val smsToUri = Uri.parse("smsto:$phone")
    val intent = Intent(Intent.ACTION_SENDTO, smsToUri)
    intent.putExtra("sms_body", body)
    startActivity(intent)
}