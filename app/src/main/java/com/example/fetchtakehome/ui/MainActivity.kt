package com.example.fetchtakehome.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.example.feature_domain.model.FetchItem
import com.example.feature_domain.model.IDisplayItem
import com.example.feature_domain.repository.FeatureRepo
import com.example.feature_ui.view.FeatureActivity
import com.example.fetchtakehome.R
import com.example.network.extensions.CoreResult

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

        startActivity(Intent(this@MainActivity, FeatureActivity::class.java))

        /*lifecycleScope.launch {
            when(val response = repo.getFetchList()) {
                is CoreResult.OnSuccess-> {
                    Log.d("BMK", "sux: ${response.getResultData?.size}")

                    val display = generateDisplayListWithHeaders(response.getResultData!!)

                    for (a in 0..display.indices.last) {
                        Log.d("BMK", "loop: ${display[a]}")
                    }

                }
                else -> {

                }
            }
        }*/
    }
    // Create a function to generate a list of headers and contacts
    private fun generateDisplayListWithHeaders(parseItems: List<FetchItem>): List<IDisplayItem> {
        // Filter items where the name is neither null nor blank, then sort by listId and name
        val sortedItems = parseItems
            .asSequence()  // Convert to sequence for efficiency on larger datasets
            .filter { !it.name.isNullOrBlank() }
            .sortedBy { it.listId }  // First sort by listId
            .sortedBy { it.name } // Then sort by name (can be combined for better performance)
            .groupBy { it.name?.first() }
            //.toList() ?: emptyList() // Convert back to list after processing

        val listItems = mutableListOf<IDisplayItem>()
        for ((header, items) in sortedItems) {
            // Add the header (A, B, C, etc.)
            val item = IDisplayItem
            header.let {
                listItems.add(item.fromHeader(it.toString()))
            }

            items.forEach { country ->
                val itemCountry = IDisplayItem
                listItems.add(itemCountry.fromFetchItem(country))
            }

        }
        return listItems
    }
}