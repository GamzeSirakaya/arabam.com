package com.example.cars.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.cars.R
import com.example.cars.databinding.FragmentCarDetailBinding
import com.example.cars.model.CarDetail
import com.example.cars.viewmodel.CarDetailViewModel
import kotlinx.android.synthetic.main.fragment_car_detail.*


class CarDetailFragment : Fragment() {
    private lateinit var viewModel: CarDetailViewModel
    private lateinit var dataBinding: FragmentCarDetailBinding
    private var carId: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_car_detail, container, false)

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       arguments?.let {
            //carId = CarDetailFragmentArgs.fromBundle(it).carId
            carId = arguments?.getInt("carId")


        }
        viewModel = ViewModelProviders.of(this).get(CarDetailViewModel::class.java)
        viewModel.CarDetail(id = carId!!)
        observerLiveData()


    }

    private fun binding(item: CarDetail) {
        dataBinding.cardetail = item
    }


    fun observerLiveData() {

        viewModel.carLiveData.observe(viewLifecycleOwner, Observer {
            it.let { carDetail ->
                binding(carDetail)
            }

        })


    }


}