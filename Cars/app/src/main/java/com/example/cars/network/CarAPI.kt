package com.example.cars.network

import com.example.cars.model.CarDetail
import com.example.cars.model.CarList
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CarAPI {
    @GET("listing")
    fun getDataFromAPI( @Query("sort")sort:Int,
                        @Query("sortDirection")sortDirection:Int,
                        @Query("take") take: Int,
                        @Query("skip") skip: Int,
                         ): Single<List<CarList>>

    @GET("detail")
    fun getDetail(@Query("id") id: Int): Single<CarDetail>

    companion object{
        const val BASE_URL = "http://sandbox.arabamd.com/api/v1/"
        fun getService(): CarAPI{
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(CarAPI::class.java)
        }
    }
}