package com.clap.android.features.currency.data.remote.api

import com.clap.android.BuildConfig
import com.clap.android.features.currency.data.remote.entities.GetConversionResponse
import com.clap.android.features.currency.data.remote.entities.GetRatesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FixerApi {

    @GET("latest")
    suspend fun getRates(
        @Query("base") base: String = "EUR",
        @Query("access_key") apiToken: String = BuildConfig.FIXER_API_KEY
    ): GetRatesResponse

    @GET("convert")
    suspend fun convertNumber(
        @Query("from") from: String? = "EUR",
        @Query("to") to: String,
        @Query("amount") amount: Double,
        @Query("access_key") apiToken: String = BuildConfig.FIXER_API_KEY
    ): GetConversionResponse
}