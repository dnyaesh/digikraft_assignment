package com.digikraft.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.digikraft.myapplication.repository.BikeStationsRepository
import javax.inject.Inject

// Provide Factory as our BikeStationViewModel takes one parameter
class BikeStationViewModelFactory @Inject constructor(private val bikeStationsRepository: BikeStationsRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BikeStationViewModel(bikeStationsRepository) as T
    }
}