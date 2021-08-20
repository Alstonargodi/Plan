package com.example.wetterbericht.model.APIforecast


import com.google.gson.annotations.SerializedName

class Clouds(
    @SerializedName("all")
    var all: Int
)