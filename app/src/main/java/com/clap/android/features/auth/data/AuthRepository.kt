package com.clap.android.features.auth.data

import com.clap.android.common.data.db.AppPrefs
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationRepository @Inject constructor(
    val appPrefs: AppPrefs
) {

    fun login() {

    }
}