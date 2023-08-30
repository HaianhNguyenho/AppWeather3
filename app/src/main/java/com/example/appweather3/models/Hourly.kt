package com.example.appweather3.models

data class Hourly(
    val time :Int,
    val tempC : Int,
    val FeelsLikeC:Int,
    val WindChillC:Int,
    val windspeedMiles:Int,
    val weatherDesc:Int,
    val humidity:Int,
    val visibility:Int,
    val pressure: Int,
    val cloudcover:Int,
    val chanceoffog: Int,
    val chanceoffrost: Int,
    val chanceofhightemp: Int,
    val chanceofovercast: Int,
    val chanceofrain: Int,
    val chanceofremdry: Int,
    val chanceofsnow: Int,
    val chanceofsunshine: Int,
    val chanceofthunder: Int,
    val chanceofwindy: Int,
)
