package com.clap.android.features.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.clap.android.R
import com.clap.android.common.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun observeUI() {
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            tvTextHome.text = it
        })
    }

    override fun setOnClickListeners() {
        tvTextHome.setOnClickListener {
            val action = HomeFragmentDirections.actionNavHomeFragmentToCurrenciesFragment()
            findNavController().navigate(action)
        }
    }
}