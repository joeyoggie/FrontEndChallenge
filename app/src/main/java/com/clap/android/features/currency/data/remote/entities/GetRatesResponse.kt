package com.clap.android.features.currency.data.remote.entities

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class GetRatesResponse (
    val success: Boolean,
    val timestamp: Long,
    val base: String,
    val date: String,
    val rates: JsonObject,
    val error: Any?
) {
    data class CurrencyRemoteEntity(
        @SerializedName("name")
        val name: String?,
        @SerializedName("rate")
        val rate: Double?
    )
}