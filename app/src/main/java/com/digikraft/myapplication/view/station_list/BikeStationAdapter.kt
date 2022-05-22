package com.digikraft.myapplication.view.station_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.digikraft.myapplication.R
import com.digikraft.myapplication.model.BikeStation

class BikeStationAdapter(private val bikeStationList: ArrayList<BikeStation>) :
    RecyclerView.Adapter<BikeStationAdapter.ViewHolder>() {

    var itemClick: ((BikeStation) -> Unit)? = null


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvStationName: TextView = itemView.findViewById(R.id.tvStationName)
        val tvAvailableBikes: TextView = itemView.findViewById(R.id.tvAvailableBikes)
        val tvAvailablePlaces: TextView = itemView.findViewById(R.id.tvAvailablePlaces)

        init {
            itemView.setOnClickListener {
                itemClick?.invoke(bikeStationList[absoluteAdapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_bike_station_layout, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentBikeStation = bikeStationList[position]
        holder.tvStationName.text = currentBikeStation.properties.label

        // Calculate available bikes (Formula is bikes - (bike_racks - free_racks))
        val availableBikes =
            currentBikeStation.properties.bikes - (currentBikeStation.properties.bike_racks - currentBikeStation.properties.free_racks)
        holder.tvAvailableBikes.text = availableBikes.toString()
        //holder.tvAvailableBikes.text = currentBikeStation.properties.available_bikes.toString()

        holder.tvAvailablePlaces.text = currentBikeStation.properties.free_racks.toString()
    }

    override fun getItemCount() = bikeStationList.size
}