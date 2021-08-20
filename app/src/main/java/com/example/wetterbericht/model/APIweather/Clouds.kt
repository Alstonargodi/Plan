package com.example.wetterbericht.model.APIweather


import com.google.gson.annotations.SerializedName

class Clouds(
    @SerializedName("all")
    var all: Int
)