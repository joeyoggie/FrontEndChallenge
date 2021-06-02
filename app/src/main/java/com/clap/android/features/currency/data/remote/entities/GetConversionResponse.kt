package com.clap.android.features.currency.data.remote.entities


data class GetConversionResponse(
    val success: Boolean,
    val date: String,
    val info: Info,
    val result: Double,
    val error: Any?
) {
    data class Info(
        val timestamp: Long,
        val rate: Double
    )
}