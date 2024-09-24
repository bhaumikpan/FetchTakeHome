package com.example.feature_data.repoImplTest

import android.util.Log
import io.mockk.coEvery
import io.mockk.mockk

import com.example.feature_data.api.FeatureApi
import com.example.feature_data.repo.FeatureRepoImpl
import com.example.feature_domain.model.FetchItem
import com.example.network.extensions.CoreResult


import io.mockk.every
import io.mockk.mockkStatic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi

import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RepoImplTest {

    private val dispatcher = UnconfinedTestDispatcher()
    private val api = mockk<FeatureApi>(relaxed = true)

    private var featureRepoImpl = FeatureRepoImpl(dispatcher, api)
    private var throwable = mockk<Throwable>(relaxed = true)

    init {
        // for any android logger used in impl code
        mockkStatic(Log::class)
        every { Log.v(any(), any()) } returns 0
        every { Log.d(any(), any()) } returns 0
        every { Log.i(any(), any()) } returns 0
        every { Log.e(any(), any()) } returns 0
    }

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Reset main dispatcher to the original Main dispatcher
    }

    @Test
    fun `test success Feature Response`() = runTest {
        val response = getDummyResponseObject()
        // given
        coEvery {
            api.getItems()
        } returns response

        // when
        when (val answer = featureRepoImpl.getFetchList()) {
            // then
            is CoreResult.OnSuccess -> {
                val data = answer.getResultData
                assert(data.size == 1)
                assert(data[0].listId == 101)
                assert(data[0].name == "Test")
                assert(data[0].id == 121)
            }
            else -> {
                assert(false)
            }
        }
    }

    @Test
    fun `test failure Feature Response`() {
        // Given
        coEvery {
            api.getItems()
        } throws throwable

        runTest {
            // When
            when (featureRepoImpl.getFetchList()) {
                // Then
                is CoreResult.OnError -> {
                    assert(true)
                }
                else -> {
                    assert(false)
                }
            }

        }
    }

    private fun getDummyResponseObject(): List<FetchItem> {
        return listOf(
            FetchItem(
                listId = 101,
                name = "Test",
                id = 121
            )
        )
    }
}