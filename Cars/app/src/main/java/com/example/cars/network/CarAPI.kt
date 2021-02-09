package com.example.cars.network

import com.example.cars.model.CarList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CarAPI {
    @GET("listing")
    fun getDataFromAPI(@Query("sort")sort :Int,
                       @Query("sortDirection")sortDirection :Int,
                       @Query("take")take :Int): Single<List<CarList>>
}