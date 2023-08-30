package com.example.appweather3.models

data class Weather(
    val astronomy: List<Astronomy>,
    val date: String,
    val maxtempC: Int,
    val hourly: List<Hourly>,
    val mintempC: Int,
)
