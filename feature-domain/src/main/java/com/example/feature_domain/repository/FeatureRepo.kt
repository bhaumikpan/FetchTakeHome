package com.example.feature_domain.repository

import com.example.feature_domain.model.Country

interface FeatureRepo {
    suspend fun getCountryList(): Result<List<Country>>
}