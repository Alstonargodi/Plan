package com.example.wetterbericht.model.api


import com.google.gson.annotations.SerializedName

data class Request(
    @SerializedName("language")
    var language: String,
    @SerializedName("query")
    var query: String,
    @SerializedName("type")
    var type: String,
    @SerializedName("unit")
    var unit: String
)