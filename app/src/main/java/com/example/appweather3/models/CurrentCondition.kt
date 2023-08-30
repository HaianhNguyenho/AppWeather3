package com.example.appweather3.models

data class CurrentCondition(
    val humidity: Int,
    val observation_time: String,
    val cloudcover: String,
    val FeelsLikeC : Int,
    val windspeedKmph:Int,
    val pressure: Int,
    val weatherDesc:String,
    val visibility: Int,
    val weatherCode: Int

)
