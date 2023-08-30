package com.example.appweather3.service

import com.example.appweather3.models.AppWeather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("weather.ashx")
    fun getWeatherData(
        @Query("q") city:String,
        @Query("key") key:String,
        @Query("num_of_days") days:Int
    ):Call<AppWeather>
}