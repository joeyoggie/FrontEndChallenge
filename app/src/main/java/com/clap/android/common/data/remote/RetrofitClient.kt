package com.clap.android.common.data.remote

import com.clap.android.BuildConfig
import com.clap.android.common.utils.Constants.CONNECTION_TIMEOUT
import com.clap.android.common.utils.Constants.READ_TIMEOUT
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitClient @Inject constructor() {

    private val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
    private val retrofit: Retrofit

    private val logger: HttpLoggingInterceptor
        get() {
            val loggingInterceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                loggingInterceptor.apply { level = HttpLoggingInterceptor.Level.BODY }
            }
            return loggingInterceptor
        }

    init {
        okHttpBuilder.apply {
            addNetworkInterceptor(logger)
            readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
            connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
            retryOnConnectionFailure(true)
        }
        val client = okHttpBuilder.build()

        val gson = GsonBuilder()
            .setLenient()
            .serializeNulls()
            .create()

        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.FIXER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    fun <S> getService(service: Class<S>): S {
        return retrofit.create(service)
    }
}
