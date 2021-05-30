package com.clap.android.common.utils

import com.clap.android.BuildConfig

object  Constants {

    object NOTIFICATIONS {
        const val CHANNEL_ID = "44"
    }

    @JvmField
    val READ_TIMEOUT = if (BuildConfig.DEBUG) 60 else 30

    @JvmField
    val CONNECTION_TIMEOUT = if (BuildConfig.DEBUG) 60 else 30
}