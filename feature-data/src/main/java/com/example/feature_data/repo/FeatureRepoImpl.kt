package com.example.feature_data.repo

import com.example.feature_domain.model.Country
import com.example.feature_domain.repository.FeatureRepo
import com.example.feature_data.api.FeatureApi
import com.example.network.di.IoDispatcher
//import com.example.network_data.di.IoDispatcher
//import com.example.network_data.extensions.CoreResult

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

import javax.inject.Inject

class FeatureRepoImpl @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val api: FeatureApi
) : FeatureRepo {
    override suspend fun getCountryList(): Result<List<Country>> {
        return withContext(dispatcher) {
            runCatching {
                api.getCountryList()
            }.fold(
                onSuccess = { it ->
                    Result.success(it.sortedBy { it.code })
                },
                onFailure = { Result.failure(it) }
            )
        }
    }
}