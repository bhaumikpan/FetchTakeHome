package com.example.feature_data.repoImplTest

import android.util.Log
import io.mockk.coEvery
import io.mockk.mockk

import com.example.feature_data.api.FeatureApi
import com.example.feature_data.repo.FeatureRepoImpl
import com.example.feature_domain.model.Country


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
            api.getCountryList()
        } returns response

        // when
        when (val answer = featureRepoImpl.getCountryList()) {
            // then
            /*is Result.success -> {
                val data = answer.data
                assert(data.isNotEmpty())
                assert(data[0].capital == "USA")
            }*/

            else -> {
                assert(false)
            }
        }
    }

    @Test
    fun `test failure Feature Response`() {
        // Given
        coEvery {
            api.getCountryList()
        } throws throwable

        runTest {
            // When
            when (featureRepoImpl.getCountryList()) {
                // Then
              /*  is CoreResult.OnError -> {
                    assert(true)
                }
*/
                else -> {
                    assert(false)
                }
            }

        }
    }

    private fun getDummyResponseObject(): List<Country> {
        return listOf(
            Country(
                capital = "USA"
            )
        )
    }
}