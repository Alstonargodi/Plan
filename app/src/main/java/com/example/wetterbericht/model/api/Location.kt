package com.example.wetterbericht.model.api


import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("country")
    var country: String,
    @SerializedName("lat")
    var lat: String,
    @SerializedName("localtime")
    var localtime: String,
    @SerializedName("localtime_epoch")
    var localtimeEpoch: Int,
    @SerializedName("lon")
    var lon: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("region")
    var region: String,
    @SerializedName("timezone_id")
    var timezoneId: String,
    @SerializedName("utc_offset")
    var utcOffset: String
)