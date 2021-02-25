package com.example.cars.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.cars.model.CarList
import com.example.cars.network.CarAPI
import io.reactivex.disposables.CompositeDisposable

class CarDataSourceFactory(private val disposable: CompositeDisposable,
private val carAPI: CarAPI,
private val sort:Int,
private val sortDirection:Int
) : DataSource.Factory<Int, CarList>() {


    private val carLiveDataSource = MutableLiveData<CarDataSource>()
    override fun create(): DataSource<Int, CarList> {
        val cardataSource = CarDataSource(carAPI = carAPI,disposable = disposable,sort = sort,sortDirection = sortDirection)

        carLiveDataSource.postValue(cardataSource)
        return cardataSource
    }
}