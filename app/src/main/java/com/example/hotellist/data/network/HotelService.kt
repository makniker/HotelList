package com.example.hotellist.data.network

import retrofit2.http.GET
import retrofit2.http.Path

interface HotelService {
    @GET("0777.json")
    suspend fun fetchHotelList(): List<HotelDataModel>

    @GET("{hotelId}.json")
    suspend fun fetchHotelDetails(@Path(value = "hotelId", encoded = true) userId: Int): HotelDetailsDataModel
}