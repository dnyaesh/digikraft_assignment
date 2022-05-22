package com.digikraft.myapplication.view.station_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.digikraft.myapplication.R
import com.digikraft.myapplication.model.BikeStation
import com.digikraft.myapplication.model.GenericApiResponse
import com.digikraft.myapplication.view.station_details.StationDetailsFragment
import com.digikraft.myapplication.viewmodel.BikeStationViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [BikeStationListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BikeStationListFragment : Fragment() {

    private val bikeStationList = ArrayList<BikeStation>()
    lateinit var bikeStationAdapter: BikeStationAdapter

    lateinit var rvBikeStations: RecyclerView
    lateinit var loader: ProgressBar
    private val bikeStationViewModel: BikeStationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_bike_station_list, container, false)

        rvBikeStations = view.findViewById(R.id.rvBikeStations)
        loader = view.findViewById(R.id.loader)

        bikeStationAdapter = BikeStationAdapter(bikeStationList)

        // List item click
        bikeStationAdapter.itemClick = { bikeStation ->
            bikeStationViewModel.selectedItem.value = bikeStation

            // Start detail fragment
            val stationDetailsFragment = StationDetailsFragment.newInstance()
            requireActivity().supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, stationDetailsFragment)
                .addToBackStack(StationDetailsFragment.javaClass.canonicalName)
                .commit()
        }

        // Init recycler view
        rvBikeStations.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = bikeStationAdapter
            isNestedScrollingEnabled = false
        }

        // Observe API response
        bikeStationViewModel.bikeStationList.observe(this, {

            when (it) {
                // Loading scenario
                is GenericApiResponse.Loading -> {
                    loader.visibility = View.VISIBLE
                }

                // Success scenario
                is GenericApiResponse.Success -> {
                    loader.visibility = View.GONE
                    it.data?.features?.let { list -> bikeStationList.addAll(list) }
                    bikeStationAdapter.notifyDataSetChanged()
                }

                // Error scenario
                is GenericApiResponse.Error -> {
                    loader.visibility = View.GONE
                    Toast.makeText(activity, it.errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        })

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment BikeStationListFragment.
         */
        @JvmStatic
        fun newInstance() =
            BikeStationListFragment()
    }
}