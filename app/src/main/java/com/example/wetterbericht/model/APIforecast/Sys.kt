package com.example.wetterbericht.model.APIforecast


import com.google.gson.annotations.SerializedName

class Sys(
    @SerializedName("pod")
    var pod: String
)