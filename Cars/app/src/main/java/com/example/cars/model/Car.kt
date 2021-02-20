package com.example.cars.model
import com.google.gson.annotations.SerializedName

data class Location (

    @SerializedName("cityName") val cityName : String,
    @SerializedName("townName") val townName : String
)
data class Category (

    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String
)
data class Properties (

    @SerializedName("name") val name : String,
    @SerializedName("value") val value : Int
)
data class CarList (

    @SerializedName("id") val id : Int,
    @SerializedName("title") val title : String,
    @SerializedName("location") val location : Location,
    @SerializedName("category") val category : Category,
    @SerializedName("modelName") val modelName : String,
    @SerializedName("price") val price : Int,
    @SerializedName("priceFormatted") val priceFormatted : String,
    @SerializedName("date") val date : String,
    @SerializedName("dateFormatted") val dateFormatted : String,
    @SerializedName("photo") val photo : String,
    @SerializedName("properties") val properties : List<Properties>
)