package com.example.hotellist.presentation.details

data class HotelDetailsUiModel(
    val id: Int,
    val name: String,
    val address: String,
    val starsString: String,
    val distanceString: String,
    val image: String,
    val suites: String,
    val lat: Double,
    val lon: Double,
)
