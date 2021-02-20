package com.example.cars.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cars.model.CarList
import com.example.cars.network.CarInstance
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CarListViewModel() : ViewModel() {
    val cars = MutableLiveData<List<CarList>>()
    val carserror = MutableLiveData<Boolean>()
    val carsloading = MutableLiveData<Boolean>()
    private val carInstance = CarInstance()
    private val disposable = CompositeDisposable()

    fun refreshData() {
        getCarData()
    }
    private fun getCarData() {
        carsloading.value = true

        disposable.add(
            carInstance.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object:DisposableSingleObserver<List<CarList>>(){
                    override fun onSuccess(t: List<CarList>) {
                        //basariliolursa
                        cars.value=t
                        carserror.value=false
                        carsloading.value=false
                    }

                    override fun onError(e: Throwable) {
                        //hataalirsak
                       carserror.value=true
                        carsloading.value=false
                        e.printStackTrace()
                    }

                })


        )
    }


}
