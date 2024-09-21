package com.example.feature_domain.model

sealed interface IDisplayItem {
    val country: Country
    val header: String

    data class CountryItem(
        override val country: Country = Country(),
        override val header: String = "",
    ) : IDisplayItem

    data class Header (
        override val country: Country = Country(),
        override val header: String = ""
    ): IDisplayItem

    companion object  {
        fun fromCountry (item: Country): IDisplayItem {
             return CountryItem(country = item)
        }

        fun fromHeader (head: String): IDisplayItem {
            return Header(header = head)
        }
    }
}