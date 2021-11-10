package net.portes.shared.extensions

import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_CALL
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import timber.log.Timber
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

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

fun String?.value(default: String = ""): String = this ?: default

fun Context.sms(phone: String?, body: String = "") {
    val smsToUri = Uri.parse("smsto:$phone")
    val intent = Intent(Intent.ACTION_SENDTO, smsToUri)
    intent.putExtra("sms_body", body)
    startActivity(intent)
}

fun Context?.toast(@StringRes textId: Int, duration: Int = Toast.LENGTH_LONG) = this?.let { Toast.makeText(it, textId, duration).show() }

fun Fragment?.toast(@StringRes textId: Int, duration: Int = Toast.LENGTH_LONG) = this?.let { activity.toast(textId, duration) }

fun <T> LiveData<T>.getThisValue(): T {
    val data = arrayOfNulls<Any>(1)
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(o: T?) {
            data[0] = o
            latch.countDown()
            removeObserver(this)
        }
    }
    observeForever(observer)
    latch.await(2, TimeUnit.SECONDS)

    @Suppress("UNCHECKED_CAST")
    return data[0] as T
}