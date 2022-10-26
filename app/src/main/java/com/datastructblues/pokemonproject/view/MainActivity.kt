package com.datastructblues.pokemonproject.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.datastructblues.pokemonproject.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}