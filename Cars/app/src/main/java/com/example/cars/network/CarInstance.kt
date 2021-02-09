package com.example.cars.network

import com.example.cars.model.CarList
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CarInstance {

    private val BASE_URL = "http://sandbox.arabamd.com/api/v1/";

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(CarAPI::class.java)

    fun getData(): Single<List<CarList>> {
        return api.getDataFromAPI(0, 1, 50)
    }

}

