package com.example.wetterbericht.model.api

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
/*
    Keep all data current weather in  Room so is can be useful without network
 */


@Entity(tableName = "dataweather")
data class Current(
    //basic
    @SerializedName("cloudcover")
    var cloudcover: Int,
    @SerializedName("feelslike")
    var feelslike: Int,
    @SerializedName("humidity")
    var humidity: Int,
    @SerializedName("is_day")
    var isDay: String,
    @SerializedName("observation_time")
    var observationTime: String,
    @SerializedName("precip")
    var precip: Int,
    @SerializedName("pressure")
    var pressure: Int,
    @SerializedName("temperature")
    var temperature: Int,
    @SerializedName("uv_index")
    var uvIndex: Int,
    @SerializedName("visibility")
    var visibility: Int,
    @SerializedName("weather_code")
    var weatherCode: Int,
    @SerializedName("weather_descriptions")
    var weatherDescriptions: List<String>,
    @SerializedName("weather_icons")
    var weatherIcons: List<String>,
    @SerializedName("wind_degree")
    var windDegree: Int,
    @SerializedName("wind_dir")
    var windDir: String,
    @SerializedName("wind_speed")
    var windSpeed: Int
)