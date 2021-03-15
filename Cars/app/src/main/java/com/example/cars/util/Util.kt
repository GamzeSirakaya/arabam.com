package com.example.cars.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.cars.R

fun ImageView.gorsel(url:String?,placeholder: CircularProgressDrawable){
    val optios=RequestOptions().placeholder(placeholder).error(R.mipmap.ic_launcher)
    Glide.with(context).setDefaultRequestOptions(optios).load(url).into(this)
}
fun makeplaceholder(context: Context):CircularProgressDrawable{
    return  CircularProgressDrawable(context).apply {
        strokeWidth=8f
        centerRadius=40f
        start()
    }

}
@BindingAdapter("android:downloadImage")
fun downloadImage(view: ImageView,url: String?){
    view.gorsel(url?.replace("{0}","800x600"), makeplaceholder(view.context))
}