package com.example.cars.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.cars.R
import com.example.cars.databinding.PhotoBinding
import com.example.cars.util.CarClickListener

class ViewPagerAdapter(private var photos: ArrayList<String>) :
    RecyclerView.Adapter<ViewPagerAdapter.Pager2ViewHolder>(), CarClickListener {


    inner class Pager2ViewHolder(var view: PhotoBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(photos: String?) {
            view.photo = photos
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerAdapter.Pager2ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<PhotoBinding>(
            inflater,
            R.layout.photo, parent, false
        )
        return Pager2ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewPagerAdapter.Pager2ViewHolder, position: Int) {
        holder.bind(photos[position])
        holder.view.listener = this
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    fun addList(newImageList: List<String>) {
        photos.clear()
        photos.addAll(newImageList)
        notifyDataSetChanged()
    }

    override fun carClick(view: View) {
        val bundle = bundleOf("photos" to photos)
        Navigation.findNavController(view)
            .navigate(R.id.action_detailFragment_to_viewPagerFragment, bundle)
    }
}