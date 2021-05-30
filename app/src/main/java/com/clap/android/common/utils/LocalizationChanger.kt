package com.clap.android.common.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.clap.android.common.data.db.AppPrefs
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalizationChanger @Inject constructor(
    @ApplicationContext val context: Context,
    val appPrefs: AppPrefs
) {

    companion object {
        const val ENGLISH = "en"
        const val ARABIC = "ar"
    }

    fun setNewLocalization(activity: Activity, language: String) {
        (context as MyApp).changeLocalization(language)
        val intent = Intent(activity.applicationContext, activity.javaClass)
        activity.startActivity(intent)
        activity.finish()
    }

    val localization: String
        get() = appPrefs.locale
}