package com.example.hotellist.presentation.core

import com.example.hotellist.presentation.list.HotelListUiModel

abstract class SortStrategy<T>(val isAscending: Boolean) {
    abstract fun sort(items: List<T>): List<T>
}

class SortBySuites(isAscending: Boolean) : SortStrategy<HotelListUiModel>(isAscending) {
    override fun sort(items: List<HotelListUiModel>): List<HotelListUiModel> {
        return if (isAscending) {
            items.sortedBy { it.suitesNum }
        } else {
            items.sortedByDescending { it.suitesNum }
        }
    }

}

class SortByDistance(isAscending: Boolean) : SortStrategy<HotelListUiModel>(isAscending) {
    override fun sort(items: List<HotelListUiModel>): List<HotelListUiModel> {
        return if (isAscending) {
            items.sortedBy { it.distance }
        } else {
            items.sortedByDescending { it.distance }
        }
    }

}
