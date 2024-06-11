package com.example.hotellist.domain

import android.content.Context
import com.example.hotellist.R
import com.example.hotellist.data.repository.HotelRepository
import com.example.hotellist.presentation.list.HotelListUiModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GetListUseCase @Inject constructor(private val hotelRepository: HotelRepository, @ApplicationContext val context: Context) {
    suspend operator fun invoke(): List<HotelListUiModel> = hotelRepository.fetchHotelList().map {
        HotelListUiModel(
            it.id,
            it.name,
            it.address,
            it.stars,
            it.distance,
            it.suites.split(":").size,
            starsString = context.getString(R.string.star_format, it.stars.toString()),
            distanceString = context.getString(R.string.distance_format, it.distance.toString()),
            suitesNumString = context.getString(
                R.string.suites_format,
                it.suites.split(":").size.toString()
            )
        )
    }
}
