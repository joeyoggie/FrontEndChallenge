package com.clap.android.common.utils

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import com.blongho.country_data.World
import com.clap.android.R
import com.clap.android.common.data.local.db.AppDatabase
import com.clap.android.common.data.local.db.AppPrefs
import com.clap.android.common.di.ApplicationScope
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltAndroidApp
class MyApp : Application() {

    @Inject
    @ApplicationScope
    lateinit var applicationScope: CoroutineScope

    @Inject
    lateinit var appDatabase: AppDatabase

    @Inject
    lateinit var appPrefs: AppPrefs

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)

        val locale = appPrefs.locale
        LocaleUtils.setLocale(Locale(locale))
        LocaleUtils.updateConfig(this, baseContext.resources.configuration)

        createNotificationChannel()

        World.init(this)
    }

    fun changeLocalization(language: String) {
        appPrefs.locale = language
        LocaleUtils.setLocale(Locale(language))
        LocaleUtils.updateConfig(this, baseContext.resources.configuration)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocaleUtils.updateConfig(this, newConfig)
    }

    fun clearData() {
        applicationScope.launch {
            appDatabase.clearAllTables()
            val locale = appPrefs.locale
            appPrefs.clear()
            changeLocalization(locale)
        }
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel =
                NotificationChannel(Constants.NOTIFICATIONS.CHANNEL_ID, name, importance).apply {
                    description = descriptionText
                }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

}