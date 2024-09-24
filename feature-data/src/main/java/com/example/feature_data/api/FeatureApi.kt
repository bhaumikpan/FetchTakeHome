package com.example.feature_data.api

import com.example.feature_domain.model.FetchItem

import retrofit2.http.GET

interface FeatureApi {
    //{baseUrl}hiring.json
    @GET("hiring.json")
    suspend fun getItems(): List<FetchItem>
}