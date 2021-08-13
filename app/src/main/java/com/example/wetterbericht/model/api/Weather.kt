package com.example.wetterbericht.model.api


import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("current")
    val current: Current,
    @SerializedName("location")
    val location: Location,
    @SerializedName("request")
    var request: Request
)