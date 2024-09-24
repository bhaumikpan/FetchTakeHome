package com.example.feature_domain

import com.example.feature_domain.model.FetchItem
import com.example.feature_domain.model.IDisplayItem

object Util {
    val baseUrl = "https://fetch-hiring.s3.amazonaws.com/"

    // todo ideally it should be moved to  display-usecase (business logic) module
    // Create a function to generate a list of headers and Item
    fun generateDisplayListWithHeaders(displayList: List<FetchItem>): List<IDisplayItem> {
        val filteredSortedItems =
            displayList
                .groupBy { it.listId }
                .flatMap { (listId, items) ->
                    // Create headers and map items in one go
                    sequenceOf(IDisplayItem.fromHeader(listId.toString())) + items.map {
                        IDisplayItem.fromFetchItem(
                            it
                        )
                    }
                }
                .toList()

        return filteredSortedItems
    }
}