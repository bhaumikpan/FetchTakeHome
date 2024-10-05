package com.example.feature_domain.repository

import com.example.feature_domain.model.FetchItem
import com.example.network.extensions.CoreResult

interface FeatureRepo {
    suspend fun getFetchList(): CoreResult<List<FetchItem>>

    suspend fun getFilteredList(): CoreResult<List<FetchItem>>
}