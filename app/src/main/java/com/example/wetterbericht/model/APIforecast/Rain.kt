package com.example.wetterbericht.model.APIforecast


import com.google.gson.annotations.SerializedName

class Rain(
    @SerializedName("3h")
    var h: Any
)