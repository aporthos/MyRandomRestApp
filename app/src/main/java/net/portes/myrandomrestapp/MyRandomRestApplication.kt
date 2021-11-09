package net.portes.myrandomrestapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import net.portes.myrandomrestapp.BuildConfig
import timber.log.Timber

/**
 * @author amadeus.portes
 */
@HiltAndroidApp
class MyRandomRestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}