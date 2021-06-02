package com.clap.android.features.currency.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.clap.android.R
import com.clap.android.common.data.state.StateData
import com.clap.android.common.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_convert_currency.*
import kotlinx.android.synthetic.main.fragment_convert_currency.pbLoading

@AndroidEntryPoint
class ConvertCurrencyFragment : BaseFragment() {

    private val viewModel: ConvertCurrencyViewModel by viewModels()

    val args: ConvertCurrencyFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_convert_currency, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvBaseName.text = args.baseCurrency
        tvSelectedName.text = args.selectedCurrency

        edtInput.requestFocus()
    }

    override fun observeUI() {
        viewModel.resultLiveData.observe(viewLifecycleOwner, Observer {
            pbLoading.isVisible = it.status == StateData.DataStatus.LOADING
            when(it.status) {
                StateData.DataStatus.SUCCESS -> {
                    edtOutput.setText(it.data.toString())
                }
                StateData.DataStatus.ERROR -> {
                    hideKeyboard()
                    it.error?.let {
                        showSnackBar(it)
                    }
                }
            }
        })
    }

    override fun setOnClickListeners() {
        edtInput.doAfterTextChanged {
            if(it.toString().isNotEmpty()) {
                viewModel.convert(it.toString().toDouble(), args.baseCurrency, args.selectedCurrency)
            } else {
                edtOutput.setText("0.0")
            }
        }
    }

}