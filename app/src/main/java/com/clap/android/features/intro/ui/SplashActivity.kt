package com.clap.android.features.intro.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.clap.android.MainActivity
import com.clap.android.R
import com.clap.android.common.ui.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity() {

    private val viewModel: SplashViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        addActivityMargins()

        observeUI()
        viewModel.isLoggedIn()
    }

    private fun observeUI() {
        viewModel.loggedIn.observe(this, Observer {
            if(it.peekContent()) {
                navigateToMainActivity()
            } else {
                navigateToAuthActivity()
            }
        })
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun navigateToAuthActivity() {
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}