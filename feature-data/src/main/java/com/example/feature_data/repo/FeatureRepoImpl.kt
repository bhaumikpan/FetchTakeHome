package com.example.feature_data.repo

import com.example.feature_domain.repository.FeatureRepo
import com.example.feature_data.api.FeatureApi
import com.example.feature_domain.model.FetchItem

import com.example.network.di.IoDispatcher
import com.example.network.extensions.CoreResult

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

import javax.inject.Inject

class FeatureRepoImpl @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val api: FeatureApi
) : FeatureRepo {
    override suspend fun getFetchList(): CoreResult<List<FetchItem>> {
        return withContext(dispatcher) {
            runCatching {
                api.getItems()
            }.fold(
                onSuccess = { fetchItems ->
                    // Filter items where the name is neither null nor blank, then sort by listId and name
                    val sortedItems = fetchItems
                        .asSequence()  // Keep as a sequence for efficiency on larger datasets
                        .filter { !it.name.isNullOrBlank() }  // Filter out null or blank names
                        .sortedWith(
                            compareBy(
                                { it.listId },
                                { it.id })
                        )  // Sort by listId first, then by name (using id which is same as name in Int)
                        .toList() ?: emptyList() // Convert back to list after processing
                    CoreResult.OnSuccess(sortedItems)
                },
                onFailure = { CoreResult.OnError(it) }
            )
        }
    }
}