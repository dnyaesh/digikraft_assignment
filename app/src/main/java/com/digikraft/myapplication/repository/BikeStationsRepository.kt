package com.digikraft.myapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.digikraft.myapplication.model.BikeStationResponse
import com.digikraft.myapplication.model.GenericApiResponse
import com.digikraft.myapplication.retrofit.AppApi
import java.lang.Exception
import javax.inject.Inject

class BikeStationsRepository @Inject constructor(private val appApi: AppApi) {

    private val _mutableBikeStationList = MutableLiveData<GenericApiResponse<BikeStationResponse>>()

    val bikeStationList: LiveData<GenericApiResponse<BikeStationResponse>>
        get() = _mutableBikeStationList


    /**
     * Method to call API
    */
    suspend fun getBikeStations() {

        try {
            _mutableBikeStationList.postValue(GenericApiResponse.Loading())

            val apiResponse = appApi.getBikeStations()

            if (apiResponse.isSuccessful && apiResponse.body() != null) {
                _mutableBikeStationList.postValue(GenericApiResponse.Success(apiResponse.body()))
            } else {
                _mutableBikeStationList.postValue(GenericApiResponse.Error("Something wen't wrong. Please try again later"))
            }

        } catch (ex: Exception) {
            _mutableBikeStationList.postValue(GenericApiResponse.Error(ex.message))
        }
    }
}