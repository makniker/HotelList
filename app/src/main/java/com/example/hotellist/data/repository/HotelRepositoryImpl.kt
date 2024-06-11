package com.example.hotellist.data.repository

import com.example.hotellist.data.network.HotelDataModel
import com.example.hotellist.data.network.HotelDetailsDataModel
import com.example.hotellist.data.network.HotelService
import javax.inject.Inject

class HotelRepositoryImpl @Inject constructor(private val service: HotelService): HotelRepository {
    override suspend fun fetchHotelList(): List<HotelDataModel> = service.fetchHotelList()

    override suspend fun fetchHotelDetails(id: Int): HotelDetailsDataModel = service.fetchHotelDetails(id)
}