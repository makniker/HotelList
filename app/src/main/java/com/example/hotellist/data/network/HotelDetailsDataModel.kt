package com.example.hotellist.data.network

import com.google.gson.annotations.SerializedName

data class HotelDetailsDataModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("address") val address: String,
    @SerializedName("stars") val stars: Int,
    @SerializedName("distance") val distance: Double,
    @SerializedName("image") val image: String,
    @SerializedName("suites_availability") val suites: String,
    @SerializedName("lat") val lat: Double,
    @SerializedName("lon") val lon: Double,
)
