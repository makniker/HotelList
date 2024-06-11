package com.example.hotellist.data.repository

import com.example.hotellist.data.network.HotelDataModel
import com.example.hotellist.data.network.HotelDetailsDataModel

interface HotelRepository {
    suspend fun fetchHotelList(): List<HotelDataModel>
    suspend fun fetchHotelDetails(id: Int): HotelDetailsDataModel
}