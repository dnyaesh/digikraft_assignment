package com.digikraft.myapplication.retrofit

import com.digikraft.myapplication.model.BikeStationResponse
import retrofit2.Response
import retrofit2.http.GET

interface AppApi {

    @GET("map_service.html?mtype=pub_transport&co=stacje_rowerowe")
    suspend fun getBikeStations(): Response<BikeStationResponse>
}