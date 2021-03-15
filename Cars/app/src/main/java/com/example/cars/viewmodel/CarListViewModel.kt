package com.example.cars.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.example.cars.model.CarList
import com.example.cars.network.CarAPI
import com.example.cars.repository.CarDataSourceFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CarListViewModel : ViewModel() {
    val cars = MutableLiveData<PagedList<CarList>>()
    val carserror = MutableLiveData<Boolean>()
    val carsloading = MutableLiveData<Boolean>()

    private val disposable = CompositeDisposable()
    private val pageSize = 50
    private lateinit var sourceFactory: CarDataSourceFactory


    fun refreshData() {
        getCarData(1, 0)
    }

    private fun getCarData(sort: Int, sortDirection: Int) {


        sourceFactory = CarDataSourceFactory(
            disposable,
            CarAPI.getService(),
            sort = sort,
            sortDirection = sortDirection
        )

        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setEnablePlaceholders(false)
            .build()

        val eventPagedList = RxPagedListBuilder(sourceFactory, config)
            .setFetchScheduler(Schedulers.io())
            .buildObservable()
            .cache()
        carsloading.value = true

        disposable.add(eventPagedList.
        subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).doOnSubscribe { carsloading.value = true }
            .subscribe({
                if (it.isNotEmpty()){
                    cars.value = it
                    carserror.value = false
                    carsloading.value = false
                }
            }, {
                carserror.value = true
                carsloading.value = false

            })
        )


    }


}
