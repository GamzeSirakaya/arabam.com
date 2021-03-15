package com.example.cars.model

import com.google.gson.annotations.SerializedName

data class CarDetail (
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("location") val location: Location,
    @SerializedName("category") val category: Category,
    @SerializedName("modelName") val modelName: String,
    @SerializedName("price") val price: Double,
    @SerializedName("priceFormatted") val priceFormatted: String,
    @SerializedName("date") val date: String,
    @SerializedName("dateFormatted") val dateFormatted: String,
    @SerializedName("photos") val photos: List<String>,
    @SerializedName("properties")   val properties: List<Properties>,
    @SerializedName("text") var text: String,
    @SerializedName("userInfo") val userInfo: UserInfo
)