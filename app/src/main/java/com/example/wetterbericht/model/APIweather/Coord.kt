package com.example.wetterbericht.model.APIweather


import com.google.gson.annotations.SerializedName

class Coord(
    @SerializedName("lat")
    var lat: Double,
    @SerializedName("lon")
    var lon: Double
)