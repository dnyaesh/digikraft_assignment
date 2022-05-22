package com.digikraft.myapplication.model

data class BikeStationResponse(val features: ArrayList<BikeStation>?)

data class BikeStation(
    val geometry: Geometry,
    val id: String,
    val type: String,
    val properties: Properties
)

data class Geometry(val arrayList: ArrayList<Double>, val type: String)


data class Properties(
    val free_racks: Int,
    val bikes: Int,
    val label: String,
    val bike_racks: Int,
    val updated: String,
    //val available_bikes: Int = bikes - (bike_racks - free_racks)
)
