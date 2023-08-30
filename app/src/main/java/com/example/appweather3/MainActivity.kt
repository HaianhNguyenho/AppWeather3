package com.example.appweather3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import com.example.appweather3.databinding.ActivityMainBinding
import com.example.appweather3.models.AppWeather
import com.example.appweather3.service.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private val binding:ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        fetchWeatherData("London,united+kingdom")
        SearchCity()
    }

    private fun SearchCity(){
        val searchView = binding.searchview
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    fetchWeatherData(query)
                }
                return true
            }


            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
    }

    private fun fetchWeatherData(cityName:String) {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://api.worldweatheronline.com/premium/v1/")
            .build().create(ApiInterface::class.java)
        val response = retrofit.getWeatherData(cityName," a1d4b7d0dc3e4fa782431103232308",5)
        response.enqueue(object : Callback<AppWeather>{
            override fun onResponse(call: Call<AppWeather>, response: Response<AppWeather>) {
               val responseBody = response.body()
                if (response.isSuccessful&&responseBody!=null){
                    val temprature = responseBody.data.tempC.toString()
                    binding.temp.text = "$temprature"
                   // Log.d("TAG", "onResponse: $temprature")
                    val hudimity = responseBody.data.humidity
                    val windspeed = responseBody.data.windspeedKmph
                    val sunRise = responseBody.data.sunrise.toLong()
                    val sunset = responseBody.data.sunset.toLong()
                    val seaLevel = responseBody.data.pressure
                    val maxTemp = responseBody.data.maxtempC
                    val minTemp = responseBody.data.mintempC
                    binding.wind.text = "$windspeed m/s"
                    binding.temp.text = "$temprature °C"
                    binding.maxTemp.text = "Max Temp: $maxTemp °C"
                    binding.minTemp.text = "Max Temp: $minTemp °C"
                    binding.humidity.text = "$hudimity %"
                    binding.sea.text = "$seaLevel hpA"
                    binding.sunset.text = "$sunset"
                    binding.day.text = dayName(System.currentTimeMillis())
                    binding.date.text = date()
                    binding.cityName.text = "$cityName"

                }
            }

            override fun onFailure(call: Call<AppWeather>, t: Throwable) {

            }

        })


    }
    private fun date():String{
        val sdf = SimpleDateFormat("dd MM yyyy", Locale.getDefault())
        return  sdf.format(Date())
    }
    fun dayName(timeTemp: Long):String{
        val sdf = SimpleDateFormat("EEEE", Locale.getDefault())
        return  sdf.format(Date())
    }
}