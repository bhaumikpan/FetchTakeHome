package com.example.network.extensions

/**
 * ResultExtension provides extension of kotlin Result class
 * if needed we can more centralized logic to handle network / any communication error handling
 * if needed we can use Default for initialization of Objects
 *
 * returning Result object as CoreResult
 */

sealed class CoreResult<out T> {
    // Represents a successful result with data
    data class OnSuccess<out T>(val data: T) : CoreResult<T>()

    // Represents an error state with a throwable or exception
    data class OnError(val error: Throwable?) : CoreResult<Nothing>()

    // Represents a default or uninitialized state
    data object Default : CoreResult<Nothing>()

    // Property to check if the result is a success
    val isSuccess: Boolean
        get() = this is OnSuccess<*>

    val getResultData: T
        get() = when (this) {
            is OnSuccess -> this.data
            is OnError -> throw IllegalStateException("Default state: No data available")
            is Default -> throw IllegalStateException("Default state: No data available")
        }
}
