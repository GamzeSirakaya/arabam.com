package com.example.cars.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cars.model.CarDetail
import com.example.cars.network.CarAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CarDetailViewModel: ViewModel() {
    val carLiveData = MutableLiveData<CarDetail>()
    private val carAPI=CarAPI
    private val compositeDisposable = CompositeDisposable()
    var detailLoading = MutableLiveData<Boolean>()
    var detailError = MutableLiveData<Boolean>()



    fun CarDetail(id: Int) {

        compositeDisposable.add(
            carAPI.getService().getDetail(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).doOnSubscribe { detailLoading.value = true }
                .subscribe({
                    if (it != null) {
                       carLiveData.value = it
                    }
                }, {
                    detailError.value = true
                }))
    }

}