package com.digikraft.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digikraft.myapplication.model.BikeStation
import com.digikraft.myapplication.model.BikeStationResponse
import com.digikraft.myapplication.model.GenericApiResponse
import com.digikraft.myapplication.repository.BikeStationsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BikeStationViewModel(private val bikeStationsRepository: BikeStationsRepository) :
    ViewModel() {

    var selectedItem = MutableLiveData<BikeStation>()

    val bikeStationList: LiveData<GenericApiResponse<BikeStationResponse>>
        get() = bikeStationsRepository.bikeStationList

    // Call API once this ViewModel class loaded
    init {
        viewModelScope.launch(Dispatchers.IO) {
            bikeStationsRepository.getBikeStations()
        }
    }

}