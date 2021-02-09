package com.example.cars.view

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


class CarListFragment : Fragment() {
    private lateinit var carview: CarListViewModel
     private val recyclerCarAdapter=CarAdapter(arrayListOf())


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
        carview = ViewModelProviders.of(this).get(CarListViewModel::class.java)
        carview.refreshData()


        recyclerViewcar.layoutManager= LinearLayoutManager(context)
        recyclerViewcar.adapter=recyclerCarAdapter
        observeLiveData()
    }

    fun observeLiveData() {
        carview.cars.observe(this, Observer {
           swiperefresh.setOnResfresh{
               recyclerViewcar.visibility=View.GONE
                carserror.visibility=View.GONE
                carsloading.visibility=View.VISIBLE
                carview.refreshData()
                swiperefresh.isRefreshing=false
            }
            recyclerViewcar.visibility=View.VISIBLE
            recyclerCarAdapter.CarListUpdate(it)
        })
        carview.carserror.observe(viewLifecycleOwner, Observer {
            if(it){
                carserror.visibility=View.VISIBLE
            }else{
                carserror.visibility=View.GONE
            }
        })
       carview.carsloading.observe(viewLifecycleOwner, Observer {
            if(it){
                recyclerViewcar.visibility=View.GONE
                carserror.visibility=View.GONE
                carsloading.visibility=View.VISIBLE
            }else{
              carsloading.visibility=View.GONE

            }
        })
    }


}