package com.clap.android.common.data.local.db

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppPrefs @Inject constructor(
    @ApplicationContext context: Context
) {

    var sharedPreferences: SharedPreferences
    var encryptedSharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.applicationContext.getSharedPreferences(
            app_preferences, Context.MODE_PRIVATE)

        val mainKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
        encryptedSharedPreferences = EncryptedSharedPreferences.create(
            context,
            app_preferences_encrypted,
            mainKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    var locale: String
        get() = sharedPreferences.getString(PREF_LOCALE, "en")!!
        set(value) {
            sharedPreferences.edit().putString(PREF_LOCALE, value).apply()
        }

    var userId: Long
        get() = sharedPreferences.getLong(PREF_LOGGED_IN_USER_ID, -1)!!
        set(value) {
            sharedPreferences.edit().putLong(PREF_LOGGED_IN_USER_ID, value).apply()
        }

    var isLoggedIn: Boolean
        get() = sharedPreferences.getBoolean(PREF_LOGGED_IN, false)
        set(value) {
            sharedPreferences.edit().putBoolean(PREF_LOGGED_IN, value).apply()
        }

    var forceUpdate: Boolean
        get() = sharedPreferences.getBoolean(PREF_FORCE_UPDATE, false)
        set(value) {
            sharedPreferences.edit().putBoolean(PREF_FORCE_UPDATE, value).apply()
        }

    var normalUpdate: Boolean
        get() = sharedPreferences.getBoolean(PREF_NORMAL_UPDATE, false)
        set(value) {
            sharedPreferences.edit().putBoolean(PREF_NORMAL_UPDATE, value).apply()
        }

    fun clear() {
        sharedPreferences.edit().clear().apply()
        encryptedSharedPreferences.edit().clear().apply()
    }

    companion object {
        private const val app_preferences = "CLAP_PREFERENCES"
        private const val app_preferences_encrypted = "CLAP_PREFERENCES_ENCRYPTED"

        private const val PREF_LOGGED_IN_USER_ID = "pref_logged_in_user_id"
        private const val PREF_LOCALE = "pref_locale"
        private const val PREF_LOGGED_IN = "pref_logged_in"
        private const val PREF_FORCE_UPDATE = "pref_force_update"
        private const val PREF_NORMAL_UPDATE = "pref_normal_update"
    }
}