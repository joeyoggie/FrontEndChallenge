package com.clap.android.features.currency.ui

import androidx.lifecycle.*
import com.clap.android.common.data.local.db.AppPrefs
import com.clap.android.common.data.state.StateData
import com.clap.android.features.currency.data.mapper.toCurrencyItem
import com.clap.android.features.currency.data.repository.CurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CurrenciesViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository,
    private val appPrefs: AppPrefs
) : ViewModel() {

    private val _defaultCurrencyLiveData: MutableLiveData<CurrencyItem> = MutableLiveData()
    val defaultCurrencyLiveData: LiveData<CurrencyItem> = _defaultCurrencyLiveData

    val ratesLiveData: LiveData<StateData<List<CurrencyItem>>> =
        currencyRepository.getRates().switchMap {
            val liveData = MediatorLiveData<StateData<List<CurrencyItem>>>()
            when (it.status) {
                StateData.DataStatus.LOADING -> {
                    liveData.postValue(StateData<List<CurrencyItem>>().loading(null))
                }
                StateData.DataStatus.SUCCESS -> {
                    liveData.postValue(StateData<List<CurrencyItem>>().success(it.data?.map { it.toCurrencyItem(appPrefs.locale) }))
                }
                StateData.DataStatus.ERROR -> {
                    it.error?.let {
                        liveData.postValue(StateData<List<CurrencyItem>>().error(it))
                    }
                }
            }
            liveData
        }

    init {
        _defaultCurrencyLiveData.postValue(
            currencyRepository.getDefaultCurrency().toCurrencyItem(appPrefs.locale)
        )
    }
}