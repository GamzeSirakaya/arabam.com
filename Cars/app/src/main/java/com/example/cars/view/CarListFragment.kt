package com.example.cars.view

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cars.R
import com.example.cars.adapter.CarAdapter
import com.example.cars.viewmodel.CarListViewModel
import kotlinx.android.synthetic.main.fragment_car_list.*


class CarListFragment : Fragment() {
    private lateinit var viewModel: CarListViewModel
    private val recyclerCarAdapter = CarAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_car_list, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CarListViewModel::class.java)
        viewModel.refreshData()
        recyclerViewcar.layoutManager = LinearLayoutManager(context)
        recyclerViewcar.adapter = recyclerCarAdapter
        observeLiveData()

    }

    fun observeLiveData() {
        viewModel.cars.observe(viewLifecycleOwner, Observer { cars ->
            cars?.let {
                recyclerViewcar.visibility = View.VISIBLE
                recyclerCarAdapter.CarListUpdate(cars)

            }

        })
        viewModel.carserror.observe(viewLifecycleOwner, Observer { carserror ->
            carserror?.let {
                if (it) {
                    noResult.visibility = View.VISIBLE
                } else {
                    noResult.visibility=View.GONE

                }
            }

        })
        viewModel.carsloading.observe(viewLifecycleOwner, Observer { carsloading->carsloading?.let {
            if (it) {
                recyclerViewcar.visibility = View.GONE
                noResult.visibility = View.GONE
               progress.visibility = View.VISIBLE
            } else {
                progress.visibility=View.GONE

            }
        }

        })
    }


}