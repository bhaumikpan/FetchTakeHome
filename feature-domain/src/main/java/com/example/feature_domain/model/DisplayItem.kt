package com.example.feature_domain.model

sealed interface IDisplayItem {
    val item: FetchItem
    val header: String

    data class DisplayItem (
        override val item: FetchItem = FetchItem(),
        override val header: String = ""
    ) :IDisplayItem

    data class HeaderItem (
        override val item: FetchItem = FetchItem(),
        override val header: String = ""
    ):IDisplayItem

    companion object  {
        fun fromFetchItem (item: FetchItem): IDisplayItem {
            return DisplayItem(item = item)
        }

        fun fromHeader (head: String): IDisplayItem {
            return HeaderItem(header = head)
        }
    }
}

/*
sealed class DisplayItem {
    val item: FetchItem
    val header: String

    data class SectionHeader(
        val header: String) : DisplayItem()
    data class Item(val item: FetchItem) : DisplayItem()  // Item is your existing data class
}*/
