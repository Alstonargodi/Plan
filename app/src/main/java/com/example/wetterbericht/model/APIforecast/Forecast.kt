package com.example.wetterbericht.model.APIforecast


import com.google.gson.annotations.SerializedName

class Forecast(
    @SerializedName("city")
    var city: City,
    @SerializedName("cnt")
    var cnt: Int,
    @SerializedName("cod")
    var cod: String,
    @SerializedName("list")
    var list: List<String>,
    @SerializedName("message")
    var message: Int
)