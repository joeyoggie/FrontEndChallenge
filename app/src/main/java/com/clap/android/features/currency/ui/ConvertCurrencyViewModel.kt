package com.clap.android.features.currency.ui

import androidx.lifecycle.*
import com.clap.android.common.data.local.db.AppPrefs
import com.clap.android.common.data.state.StateData
import com.clap.android.features.currency.data.repository.CurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConvertCurrencyViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository,
    private val appPrefs: AppPrefs
) : ViewModel() {

    private val _resultLiveData: MutableLiveData<StateData<Double>> = MutableLiveData()
    val resultLiveData: LiveData<StateData<Double>> = _resultLiveData

    fun convert(number: Double, baseCurrency: String? = null, selectedCurrency: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = currencyRepository.convertNumber(number, selectedCurrency, baseCurrency)
            _resultLiveData.postValue(response)
        }
    }

}