package com.example.hotellist.data.repository

import com.example.hotellist.data.network.HotelDataModel
import com.example.hotellist.data.network.HotelDetailsDataModel
import com.google.gson.Gson
import javax.inject.Inject

class HotelRepositoryTestImpl @Inject constructor() : HotelRepository {
    override suspend fun fetchHotelList(): List<HotelDataModel> = Gson().fromJson(
        """
            [
            	{
            		"id": 40611,
            		"name": "Belleclaire Hotel",
            		"address": "250 West 77th Street, Manhattan",
            		"stars": 3.0,
            		"distance": 100.0,
            		"suites_availability": "1:44:21:87:99:34"
            	},
            	{
            		"id": 80899,
            		"name": "Americana Inn",
            		"address": "69 West 38th Street",
            		"stars": 2.0,
            		"distance": 99.9,
            		"suites_availability": "5:8:32:54"
            	},
            	{
            		"id": 13100,
            		"name": "Best Western President Hotel at Times Square",
            		"address": "234 West 48th Street",
            		"stars": 3.0,
            		"distance": 13.10,
            		"suites_availability": "1"
            	},
            	{
            		"id": 22470,
            		"name": "Days Hotel Broadway at 94th Street",
            		"address": "215 West 94th Street",
            		"stars": 1.0,
            		"distance": 999.9,
            		"suites_availability": "15:48:115:72:81"
            	},
            	{
            		"id": 85862,
            		"name": "Dream",
            		"address": "210 W. 55 STREET, NEW YORK NY 10019, UNITED STATES",
            		"stars": 4.0,
            		"distance": 554.4,
            		"suites_availability": "42:33:22"
            	},
            	{
            		"id": 313499,
            		"name": "Dream Downtown",
            		"address": "355 West 16th Street",
            		"stars": 0,
            		"distance": 716.06,
            		"suites_availability": "2:87:24:65:26:119:202:6"
            	},
            	{
                "id": 13370,
                "name": "Midtown Lodging",
                "address": "250 East 50th Street",
                "stars": 2.0,
                "distance": 13.10,
                "suites_availability": "1:"
              }
            ]
        """.trimIndent(), Array<HotelDataModel>::class.java
    ).toList()

    override suspend fun fetchHotelDetails(id: Int): HotelDetailsDataModel = Gson().fromJson(
        """
        {
        	"id": 40611,
        	"name": "Belleclaire Hotel",
        	"address": "250 West 77th Street, Manhattan",
        	"stars": 3.0,
        	"distance": 100.0,
        	"image": "1.jpg",
        	"suites_availability": "1:44:21:87:99:34",
        	"lat": 40.78260000000000,
        	"lon": -73.98130000000000
        }
    """.trimIndent(), HotelDetailsDataModel::class.java
    )

}