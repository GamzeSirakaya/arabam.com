package com.example.cars.repository
import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.cars.model.CarList
import com.example.cars.network.CarAPI
import io.reactivex.disposables.CompositeDisposable

class CarDataSource(private val disposable: CompositeDisposable,
                    private val carAPI: CarAPI,
                    private val sort:Int,
                    private val sortDirection:Int,
                    ) : PageKeyedDataSource<Int, CarList>() {


    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, CarList>
    ) {
        val numberOfItems = params.requestedLoadSize
        createObservable(0,1,numberOfItems,callback,null)

    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, CarList>
    ) {

    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, CarList>
    ) {
        val page = params.key
        val numberOfItems = params.requestedLoadSize
        createObservable(page,page+1,numberOfItems,null,callback)
    }

    private fun createObservable(requestedPage:Int,
                                 adjacentPage:Int,
                                 requestedLoadSize:Int,
                                 initialcallback: LoadInitialCallback<Int, CarList>?,
                                 callback: LoadCallback<Int, CarList>?
    ){
        disposable.add(carAPI.getDataFromAPI(
            sort = sort,
            sortDirection = sortDirection,
            requestedLoadSize,
            requestedPage
        ).subscribe(
            { response ->
                initialcallback?.onResult(response, null, adjacentPage)
                callback?.onResult(response, adjacentPage)

            },
            {
            }
        ))
    }
}