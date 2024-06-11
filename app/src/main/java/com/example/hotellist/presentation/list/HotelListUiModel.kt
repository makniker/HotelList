package com.example.hotellist.presentation.list

data class HotelListUiModel(
    val id: Int,
    val name: String,
    val address: String,
    val stars: Int,
    val distance: Double,
    val suitesNum: Int,
    val starsString: String,
    val distanceString: String,
    val suitesNumString: String,
)
