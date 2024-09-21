package com.example.fetchtakehome.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.example.feature_domain.repository.FeatureRepo
import com.example.fetchtakehome.R

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var repo: FeatureRepo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        lifecycleScope.launch {
            val response = repo.getCountryList()
            when(response.isSuccess) {
                true -> {
                    Log.d("BMK", "sux: "+response.getOrDefault(emptyList()))
                }
                false -> {

                }
            }
        }
    }
}