package com.example.cars.view

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.service.autofill.OnClickAction
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.core.text.HtmlCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
import com.example.cars.R
import com.example.cars.adapter.ViewPagerAdapter
import com.example.cars.databinding.FragmentCarDetailBinding
import com.example.cars.model.CarDetail
import com.example.cars.util.CarClickListener
import com.example.cars.viewmodel.CarDetailViewModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_car_detail.*
import kotlinx.android.synthetic.main.fragment_car_detail.view.*
import kotlinx.android.synthetic.main.recycler_row.view.*


class CarDetailFragment : Fragment(), CarClickListener {
    private lateinit var viewModel: CarDetailViewModel
    private lateinit var dataBinding: FragmentCarDetailBinding
    private lateinit var viewPagerAdapter: ViewPagerAdapter
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

        val viewPager = dataBinding.tableInclude.tableViewpager
        val tabLayout = dataBinding.tableInclude.tableLayout
        tabLayout.setupWithViewPager(viewPager)
        arguments?.let {
            carId = arguments?.getInt("carId")


        }

        opentext.setOnClickListener {

            textdetail.visibility = View.VISIBLE

        }
        toolbar.setNavigationOnClickListener(View.OnClickListener {
            carClick(view.toolbar)
        })

        call.setOnClickListener {
            val call = view.userphone.text.toString()
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + "$call")
            startActivity(dialIntent)


        }
        message.setOnClickListener {
            val message = view.userphone.text.toString()
            val Intent = Intent(Intent.ACTION_SENDTO)
            Intent.data = Uri.parse("smsto:" + "$message")
            startActivity(Intent)

        }

        viewModel = ViewModelProviders.of(this).get(CarDetailViewModel::class.java)
        viewModel.CarDetail(id = carId!!)
        viewPagerAdapter = ViewPagerAdapter(arrayListOf())
        observerLiveData()

        val photoViewPager = dataBinding.viewpager
        val dotsIndicator = dataBinding.photoTabLayout
        photoViewPager.adapter = viewPagerAdapter
        photoViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        TabLayoutMediator(dotsIndicator, photoViewPager) { tab, position ->
        }.attach()

    }



    fun observerLiveData() {
        viewModel.carLiveData.observe(viewLifecycleOwner, Observer {
            it.let { carDetail ->
                dataBinding.cardetail = it
                viewPagerAdapter.addList(carDetail.photos)
            }

        })


    }

    override fun carClick(view: View) {
        Navigation.findNavController(view)
            .navigate(R.id.action_carDetailFragment_to_carListFragment)
    }


}