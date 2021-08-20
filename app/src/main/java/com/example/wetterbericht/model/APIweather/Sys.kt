package com.example.wetterbericht.model.APIweather


import com.google.gson.annotations.SerializedName

class Sys(
    @SerializedName("country")
    var country: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("sunrise")
    var sunrise: Int,
    @SerializedName("sunset")
    var sunset: Int,
    @SerializedName("type")
    var type: Int
)