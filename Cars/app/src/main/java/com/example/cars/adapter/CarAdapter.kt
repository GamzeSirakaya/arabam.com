package com.example.cars.adapter

import android.view.LayoutInflater

import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil

import androidx.recyclerview.widget.RecyclerView
import com.example.cars.R
import com.example.cars.databinding.RecyclerRowBinding
import com.example.cars.model.CarList

class CarAdapter(val carList: ArrayList<CarList>) : RecyclerView.Adapter<CarAdapter.CarViewHolder>() {
    class CarViewHolder(var view: RecyclerRowBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<RecyclerRowBinding>(
            inflater,
            R.layout.recycler_row,
            parent,
            false
        )
        return CarViewHolder(view)

    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.view.carlist=carList[position]
    }

    fun CarListUpdate(newCarList: List<CarList>) {


    }

    override fun getItemCount(): Int {
        return carList.size
    }


}