package com.example.hotellist.domain

import android.content.Context
import com.example.hotellist.R
import com.example.hotellist.data.repository.HotelRepository
import com.example.hotellist.presentation.details.HotelDetailsUiModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GetHotelDetailsUseCase @Inject constructor(
    private val hotelRepository: HotelRepository, @ApplicationContext val context: Context
) {
    private val imageBase =
        "https://raw.githubusercontent.com/iMofas/ios-android-test/master/"

    suspend operator fun invoke(id: Int): HotelDetailsUiModel = hotelRepository.fetchHotelDetails(id).let {
        HotelDetailsUiModel(
            id = it.id,
            name = it.name,
            address = it.address,
            starsString = context.getString(R.string.star_format, it.stars.toString()),
            distanceString = context.getString(R.string.distance_format, it.distance.toString()),
            suites = context.getString(
                R.string.suites_format, it.suites.split(":").size.toString()
            ) + ": "+ it.suites.split(":"),
            image = imageBase + it.image,
            lat = it.lat,
            lon = it.lon
        )
    }
}