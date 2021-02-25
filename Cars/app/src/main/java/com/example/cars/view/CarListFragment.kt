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
    private lateinit var carAdapter: CarAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       carAdapter = CarAdapter()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_car_list, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CarListViewModel::class.java)
        viewModel.refreshData()
        recyclerViewcar.layoutManager = LinearLayoutManager(context)
        recyclerViewcar.adapter = carAdapter
        swiperefresh.setOnRefreshListener {
            progress.visibility = View.VISIBLE
            noResult.visibility = View.GONE
            recyclerViewcar.visibility = View.GONE
            viewModel.refreshData()
            swiperefresh.isRefreshing = false

        }
        observeLiveData()

    }

    private fun observeLiveData() {
        viewModel.cars.observe(viewLifecycleOwner, { cars ->
            recyclerViewcar.visibility = View.VISIBLE
            carAdapter.submitList(cars)


        })
        viewModel.carserror.observe(viewLifecycleOwner, Observer { carserror ->
            carserror?.let {
                if (it) {
                    noResult.visibility = View.VISIBLE
                } else {
                    noResult.visibility = View.GONE

                }
            }

        })
        viewModel.carsloading.observe(viewLifecycleOwner, Observer { carsloading ->
            carsloading?.let {
                if (it) {
                    recyclerViewcar.visibility = View.GONE
                    noResult.visibility = View.GONE
                    progress.visibility = View.VISIBLE
                } else {
                    progress.visibility = View.GONE

                }
            }

        })

    }


}