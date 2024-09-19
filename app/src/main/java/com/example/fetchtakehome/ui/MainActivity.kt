package com.example.fetchtakehome.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.fetchtakehome.R

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }
}