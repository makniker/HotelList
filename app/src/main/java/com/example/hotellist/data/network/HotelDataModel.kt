package com.example.hotellist.data.network

import com.google.gson.annotations.SerializedName

data class HotelDataModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("address") val address: String,
    @SerializedName("stars") val stars: Int,
    @SerializedName("distance") val distance: Double,
    @SerializedName("suites_availability") val suites: String
)