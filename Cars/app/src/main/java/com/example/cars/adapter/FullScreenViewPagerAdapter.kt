package com.example.cars.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cars.R
import com.example.cars.databinding.FullScreenPhotoBinding


class FullScreenViewPagerAdapter(private var images : ArrayList<String>) :   RecyclerView.Adapter<FullScreenViewPagerAdapter.DetailPhotoViewHolder>() {


    inner class DetailPhotoViewHolder(var view:FullScreenPhotoBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(image:String?){
            if (image!=null){
                view.fullScreenImage.visibility = View.VISIBLE
                view.fullScreenPhoto= image
            }else{
                view.fullScreenImage.visibility = View.GONE
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailPhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view  = DataBindingUtil.inflate<FullScreenPhotoBinding>(inflater,
            R.layout.full_screen_photo,parent,false)
        return  DetailPhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailPhotoViewHolder, position: Int) {
        holder.bind(images[position])

    }

    override fun getItemCount(): Int {
        return images.size
    }


}