package com.digikraft.myapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.digikraft.myapplication.MyApplication
import com.digikraft.myapplication.R
import com.digikraft.myapplication.model.BikeStation
import com.digikraft.myapplication.model.GenericApiResponse
import com.digikraft.myapplication.view.station_list.BikeStationListFragment
import com.digikraft.myapplication.viewmodel.BikeStationViewModel
import com.digikraft.myapplication.viewmodel.BikeStationViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var bikeStationViewModelFactory: BikeStationViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as MyApplication).applicationComponent.inject(this)

        // Init ViewModel
        ViewModelProvider(
            this,
            bikeStationViewModelFactory
        ).get(BikeStationViewModel::class.java)

        loadBikeStationListFragment()
    }

    /**
     *  Method which Loads BikeStationListFragment
     */
    private fun loadBikeStationListFragment() {

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, BikeStationListFragment.newInstance())
            .commit()
    }
}