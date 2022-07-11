package com.example.wetterbericht.presentation.fragment.weather


import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.wetterbericht.R
import com.example.wetterbericht.databinding.FragmentWeatherBinding
import com.example.wetterbericht.data.local.entity.weather.WeatherLocal
import com.example.wetterbericht.data.remote.openweather.forecast.ForecastResponse
import com.example.wetterbericht.data.remote.openweather.forecast.ForecastItem
import com.example.wetterbericht.data.remote.openweather.weather.WeatherResponse
import com.example.wetterbericht.presentation.fragment.weather.adapter.ForecastRecyclerViewAdapter
import com.example.wetterbericht.viewmodel.localviewmodel.LocalViewModel
import com.example.wetterbericht.viewmodel.weatherviewmodel.WeatherViewModel
import com.example.wetterbericht.viewmodel.viewmodelfactory.ViewModelFactory
import kotlin.math.round


class WeatherFragment : Fragment(){
    private lateinit var binding : FragmentWeatherBinding
    private val weatherViewModel by viewModels<WeatherViewModel>()
    private val roomViewModel : LocalViewModel by viewModels{ ViewModelFactory.getInstance(requireContext())}
    private lateinit var forecastRecyclerViewAdapter: ForecastRecyclerViewAdapter
    private var forecastList = ArrayList<ForecastItem>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentWeatherBinding.inflate(layoutInflater)
        setHasOptionsMenu(true)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbarWeather)
        favoriteCity()
        fetchChecker()
        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.weather_toolbar,menu)
        val searchView = menu.findItem(R.id.search_weather).actionView as SearchView
        searchView.apply {
            queryHint = "Enter Location"
            setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    searchCurrentWeather(query ?: "")
                    locationChecker(query ?: "")
                    binding.tvWeatherNodata.visibility = View.GONE
                    return true
                }
                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
        }
    }

    private fun favoriteCity(){
        roomViewModel.readWeatherLocal().observe(viewLifecycleOwner) { response ->
            if (response.isNotEmpty()) {
                searchCurrentWeather(response[0].loc)
                binding.tvWeatherNodata.visibility = View.GONE
            }else{
                binding.tvWeatherNodata.visibility = View.VISIBLE
            }
        }
    }


    private fun searchCurrentWeather(city : String){
        weatherViewModel.apply {
            getWeatherSearch(city)
            weatherSearchResponse.observe(viewLifecycleOwner){
                setWeatherCard(it)
                forecastList.clear()
            }
            getWeatherForecast(city)
            forecastResponse.observe(viewLifecycleOwner){
                setWeatherForecast(it)
            }
        }
    }


    private fun locationChecker(location : String){
        roomViewModel.searchLocation(location).observe(viewLifecycleOwner){
            if (it.isNullOrEmpty()){
                binding.btnSetLocation.visibility = View.VISIBLE
            }else{
                binding.btnSetLocation.visibility = View.GONE
            }
        }
    }

    private fun setWeatherCard(data : WeatherResponse){
        binding.apply {
            data.apply {
                tvweatherLoc.text = name
                tvweatherCon.text = weather[0].description

                val temp = round(main.temp).toInt()
                val visibility = visibility / 1000

                tvweatherTemp.text = "$temp c"

                tvweatherClouds.text = "Clouds  ${clouds.all} %"
                tvweatherFeels.text =  "Feels like ${main.feelsLike} c "
                tvweatherWindspeed.text = "Wind Speed ${wind.speed} km/h"
                tvweatherVisibilty.text = "Visibilty $visibility Km"

                val url = weather[0].icon
                val icon = "http://openweathermap.org/img/w/${url}.png"

                Glide.with(requireContext())
                    .asBitmap()
                    .load(icon)
                    .into(imgIconweather)

                btnSetLocation.setOnClickListener { setCurrentLocation(data) }
            }
        }
    }

    private fun setWeatherForecast(respon : ForecastResponse){
        val data = respon.list
        for (i in data.indices){
            val date = data[i].dtTxt
            val desc = data[i].weather[0].description
            val temp = data[i].main.temp.toString()

            val tempForecast = ForecastItem(
                date,
                desc,
                temp
            )

            forecastList.add(tempForecast)
            forecastRecyclerViewAdapter = ForecastRecyclerViewAdapter(forecastList.distinct())
            val recyclerView = binding.weatherForecast
            recyclerView.adapter = forecastRecyclerViewAdapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setCurrentLocation(data : WeatherResponse){
        val temperature = round(data.main.temp).toInt()
        val iconUrl = data.weather[0].icon
        val conditionIcon = "http://openweathermap.org/img/w/${iconUrl}.png"
        val temp = WeatherLocal(
            0,
            data.name,
            temperature.toString(),
            conditionIcon,
            data.main.feelsLike.toString(),
            data.wind.speed.toString(),
            data.weather[0].description
        )
        roomViewModel.insertWeatherLocal(temp)
        Toast.makeText(requireContext(),"Set as current location", Toast.LENGTH_SHORT).show()
    }

    private fun fetchChecker(){
        weatherViewModel.isLoading.observe(viewLifecycleOwner){ loading ->
            if(loading){
                binding.pgbarWeather.visibility = View.VISIBLE
            }else{
                binding.pgbarWeather.visibility = View.GONE
            }
        }
        weatherViewModel.status.observe(viewLifecycleOwner){ status ->
            binding.tvWeatherStatus.visibility = View.VISIBLE
            binding.tvWeatherStatus.text = status
        }
    }
}