package com.example.hotels.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hotels.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

    }
}
