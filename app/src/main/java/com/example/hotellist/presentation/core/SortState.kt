package com.example.hotellist.presentation.core

data class SortState(
    var stateOfSorting: SortingState,
    var isAscending: Boolean
)

enum class SortingState {
    DEFAULT, BY_DISTANCE, BY_SUITES
}