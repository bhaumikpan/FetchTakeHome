package com.example.analytics.impl

import com.example.analytics.model.Analytics
import com.example.analytics.repo.AnalyticsRepo
import com.example.di.IoDispatcher

import kotlinx.coroutines.CoroutineDispatcher

import java.time.ZoneOffset
import java.time.ZonedDateTime

import javax.inject.Inject

class AnalyticsRepoImpl @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : AnalyticsRepo {
    private fun getCurrentUtcTimestamp(): String {
        val now = ZonedDateTime.now(ZoneOffset.UTC)
        return now.toString()
    }

    override fun submit(text: String) {
        // todo ideally it should send to api using dispatcher / for simplicity logging it
        val eventAnalytics = Analytics(text, getCurrentUtcTimestamp())
        android.util.Log.d("TAG", "Event OCCURRED: $eventAnalytics")
    }
}