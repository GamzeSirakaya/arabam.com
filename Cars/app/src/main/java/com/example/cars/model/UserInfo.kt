package com.example.cars.model

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("id")  val id: Int,
    @SerializedName("nameSurname") val nameSurname: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("phoneFormatted") val phoneFormatted: String
)