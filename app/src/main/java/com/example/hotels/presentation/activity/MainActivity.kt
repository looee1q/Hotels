package com.example.hotels.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.fragment
import com.example.hotels.R
import com.example.hotels.databinding.ActivityMainBinding
import com.example.hotels.presentation.hotelDetails.HotelDetailsFragment
import com.example.hotels.presentation.hotels.HotelsFragment
import com.example.hotels.presentation.navigation.HotelDetails
import com.example.hotels.presentation.navigation.Hotels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        setNavigationGraph()
    }

    private fun setNavigationGraph() {
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_fragment
        ) as NavHostFragment

        val navController = navHostFragment.navController

        navController.graph = navController.createGraph(
            startDestination = Hotels
        ) {
            fragment<HotelsFragment, Hotels>()
            fragment<HotelDetailsFragment, HotelDetails>()
        }
    }
}
