package com.example.cars.model


import com.google.gson.annotations.SerializedName

data class Properties (
    @SerializedName("name") val name : String,
    @SerializedName("value") val value :  String
)