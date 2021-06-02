package com.clap.android.features.currency.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.clap.android.R
import com.clap.android.common.data.state.StateData
import com.clap.android.common.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_currencies.*

@AndroidEntryPoint
class CurrenciesFragment : BaseFragment(), CurrencySelectedListener {

    private val viewModel: CurrenciesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_currencies, container, false)
    }

    override fun observeUI() {
        viewModel.defaultCurrencyLiveData.observe(viewLifecycleOwner, Observer {
            tvBaseName.text = it.name
            if(it.imageResourceId != -1) {
                ivBaseFlag.setImageResource(it.imageResourceId)
            }
        })
        viewModel.ratesLiveData.observe(viewLifecycleOwner, Observer {
            pbLoading.isVisible = it.status == StateData.DataStatus.LOADING
            when(it.status) {
                StateData.DataStatus.SUCCESS -> {
                    it.data?.let {
                        val layoutManager = LinearLayoutManager(requireContext())
                        val adapter = CurrencyAdapter(it, this)
                        rvRates.layoutManager = layoutManager
                        rvRates.adapter = adapter
                    }
                }
                StateData.DataStatus.ERROR -> {
                    it.error?.let {
                        showSnackBar(it)
                    }
                }
            }
        })
    }

    override fun setOnClickListeners() {

    }

    override fun onCurrencySelected(currency: String) {
        val action = CurrenciesFragmentDirections.actionNavCurrenciesFragmentToConvertCurrencyFragment(viewModel.defaultCurrencyLiveData.value?.name, currency)
        mNavController.navigate(action)
    }
}