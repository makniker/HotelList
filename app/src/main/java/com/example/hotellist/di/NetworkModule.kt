package com.example.hotellist.di

import com.example.hotellist.data.network.HotelService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_ENDPOINT = "https://raw.githubusercontent.com/iMofas/ios-android-test/master/"

    @Provides
    @Singleton
    fun provideRetrofit(
        gson: Gson,
    ): Retrofit = Retrofit.Builder().baseUrl(BASE_ENDPOINT)
        .addConverterFactory(GsonConverterFactory.create(gson)).build()

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideHotelService(
        retrofit: Retrofit,
    ): HotelService {
        return retrofit.create(HotelService::class.java)
    }
}