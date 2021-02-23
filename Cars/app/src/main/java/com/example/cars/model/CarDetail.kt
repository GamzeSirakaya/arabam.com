package com.example.cars.model

data class CarDetail (
    val id: Int,
    val title: String,
    val location: Location,
    val category: Category,
    val modelName: String,
    val price: Double,
    val priceFormatted: String,
    val date: String,
    val dateFormatted: String,
    val photos: List<String>,
    val properties: List<Properties>,
    var text: String,
    val userInfo: UserInfo
)