package com.example.feature_ui.util

import com.example.feature_domain.model.FetchItem
import com.example.feature_domain.model.IDisplayItem

object Util {
    // Create a function to generate a list of headers and contacts
    fun generateDisplayListWithHeaders(displayList: List<FetchItem>): List<IDisplayItem> {
        val filteredSortedItems = displayList
            .asSequence()  // Use sequence for lazy evaluation, improves performance with large datasets
           // .filter { !it.name.isNullOrBlank() }  // Filter out null or blank names
            .sortedWith(compareBy({ it.listId}, { it.id }))  // Sort by listId first, then name: using Id(Int) instead name(String)
            .groupBy { it.listId }
            .flatMap { (listId, items) ->
                // Create headers and map items in one go
                sequenceOf(IDisplayItem.fromHeader(listId.toString())) + items.map { IDisplayItem.fromFetchItem(it) }
            }
            .toList()


        return filteredSortedItems
    }
}