package com.clap.android.features.currency.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import com.blongho.country_data.World
import com.clap.android.common.data.local.db.AppDatabase
import com.clap.android.common.data.local.db.AppPrefs
import com.clap.android.common.data.remote.RetrofitClient
import com.clap.android.common.data.state.StateData
import com.clap.android.common.di.ApplicationScope
import com.clap.android.features.currency.data.local.db.entities.CurrencyEntity
import com.clap.android.features.currency.data.remote.api.FixerApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyRepository @Inject constructor(
    appDatabase: AppDatabase,
    retfofitClient: RetrofitClient,
    appPrefs: AppPrefs,
    @ApplicationScope val applicationScope: CoroutineScope,
) {

    private val fixerApi = retfofitClient.getService(FixerApi::class.java)

    private val currencyDao = appDatabase.currencyDao()

    fun getRates(): LiveData<StateData<List<CurrencyEntity>>> {
        val result = MediatorLiveData<StateData<List<CurrencyEntity>>>()

        result.addSource(currencyDao.getAll(), Observer {
            result.postValue(StateData<List<CurrencyEntity>>().success(it))
        })

        applicationScope.launch {
            val response = fixerApi.getRates()

            if(response.success) {
                val currencyEntities = ArrayList<CurrencyEntity>()
                for(rate in response.rates.entrySet()) {
                    val currencyEntity = CurrencyEntity(rate.key, rate.value.asDouble, getFlag(rate.key))
                    currencyEntities.add(currencyEntity)
                }
                currencyDao.insertAll(currencyEntities)
            } else {
                result.postValue(StateData<List<CurrencyEntity>>().error(response.error.toString()))
            }
        }

        return result
    }

    private fun getFlag(currency: String): Int {
        val country = World.getAllCurrencies().firstOrNull { it.code == currency }?.country
        var flag = -1
        country?.let {
            flag = World.getFlagOf(country)
        }
        return flag
    }

    suspend fun convertNumber(number: Double, selectedCurrency: String, baseCurrency: String? = "EUR"): StateData<Double> {
        val result = StateData<Double>()

        val response = fixerApi.convertNumber(baseCurrency, selectedCurrency, number)

        if(response.success) {
            result.success(response.result)
        } else {
            result.error(response.error.toString())
        }

        return result
    }

    fun getDefaultCurrency(): CurrencyEntity {
        return CurrencyEntity("EUR", 0.0, getFlag("EUR"))
    }
}