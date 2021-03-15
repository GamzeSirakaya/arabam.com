package com.example.cars.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.cars.R
import com.example.cars.adapter.ViewPagerAdapter
import com.example.cars.viewmodel.CarDetailViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class ViewPagerFragment : Fragment() {
    lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var carDetailViewModel: CarDetailViewModel

    private var photos: ArrayList<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       viewPagerAdapter= ViewPagerAdapter(arrayListOf())
        photos=arguments?.getStringArrayList("photos")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
    val view=inflater.inflate(R.layout.fragment_view_pager, container, false)
        carDetailViewModel=ViewModelProvider(this).get(CarDetailViewModel::class.java)

        viewPagerAdapter.addList(photos!!)
        val detailViewPager = view.findViewById<ViewPager2>(R.id.detailViewPager)
        val detailTabLayout = view.findViewById<TabLayout>(R.id.detailTabLayout)
        detailViewPager.adapter =viewPagerAdapter
        TabLayoutMediator(detailTabLayout,detailViewPager){tab,position->}.attach()
        return view
    }


}