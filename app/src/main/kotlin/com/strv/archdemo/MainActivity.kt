package com.strv.archdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBar()
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(findViewById(R.id.nav_host_fragment)).navigateUp()
    }

    // Black box. Not relevant
    private fun setupActionBar() {
        val host = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment? ?: return

        // IDs of fragments you want without the ActionBar home/up button
        appBarConfiguration = AppBarConfiguration(setOf(R.id.mainFragment))

        setupActionBarWithNavController(host.navController, appBarConfiguration)
    }
}