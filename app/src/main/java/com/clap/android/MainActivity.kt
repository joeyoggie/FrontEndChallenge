package com.clap.android

import android.os.Bundle
import androidx.core.view.isVisible
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.clap.android.common.ui.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navViewBottom: BottomNavigationView = findViewById(R.id.navViewBottom)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_homeFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navViewBottom.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.nav_homeFragment -> {
                    navViewBottom.isVisible = true
                }
                R.id.nav_currenciesFragment -> {
                    navViewBottom.isVisible = false
                }
            }
        }
    }
}