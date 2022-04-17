package com.example.wetterbericht.view.fragment


import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.wetterbericht.view.weather.DetailWeatherActivity
import com.example.wetterbericht.R
import com.example.wetterbericht.databinding.FragmentWeatherBinding
import com.example.wetterbericht.model.remote.response.ForecastResponse
import com.example.wetterbericht.model.remote.response.Foredata
import com.example.wetterbericht.model.local.WeatherLocal
import com.example.wetterbericht.model.remote.service.WeatherResponse
import com.example.wetterbericht.view.adapter.ForecastAdapter
import com.example.wetterbericht.view.adapter.WeatherRvFavoriteAdapter
import com.example.wetterbericht.viewmodel.LocalViewModel
import com.example.wetterbericht.viewmodel.WeatherViewModel
import com.example.wetterbericht.viewmodel.utils.obtainViewModel
import kotlin.math.round


class WeatherFragment : Fragment(){

    private lateinit var binding : FragmentWeatherBinding

    private val mainViewModel by viewModels<WeatherViewModel>()

    private lateinit var roomViewModel : LocalViewModel

    private lateinit var favoriteAdapter : WeatherRvFavoriteAdapter
    private lateinit var forecastAdapter: ForecastAdapter

    private var forecastList = ArrayList<Foredata>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentWeatherBinding.inflate(layoutInflater)
        setHasOptionsMenu(true)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbarWeather)
        roomViewModel = obtainViewModel(requireActivity())


        favoriteCity()

        mainViewModel.isLoading.observe(viewLifecycleOwner){
            binding.pgbarWeather.visibility = if (it) View.VISIBLE else View.GONE
        }



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
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
        }
    }

    private fun favoriteCity(){
        roomViewModel.readWeatherLocal()
        roomViewModel.responWeatherLocal.observe(viewLifecycleOwner) { response ->
            setListFavCity(response)
            if (response.isNotEmpty()){
                searchCurrentWeather(response[0].loc)
            }
        }
    }

    private fun setListFavCity(list : List<WeatherLocal>){
        favoriteAdapter = WeatherRvFavoriteAdapter()
        favoriteAdapter.setdata(list)
        val recyclerView = binding.reclist
        recyclerView.adapter = favoriteAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun searchCurrentWeather(city : String){
        mainViewModel.apply {
            getWeatherSearch(city)
            weatherSearchRespon.observe(viewLifecycleOwner){
                setWeatherCard(it)
                forecastList.clear()
            }
            getWeatherForecast(city)
            forecastRespon.observe(viewLifecycleOwner){
                setWeatherForecast(it)
            }
        }
    }

    private fun setWeatherCard(data : WeatherResponse){
        binding.apply {
            data.apply {
                tvweatherLoc.text = name
                tvweatherCon.text = weather[0].description

                val temp = round(main.temp).toInt()
                tvweatherTemp.text = temp.toString() + " c"

                tvweatherClouds.text = "Clouds " + clouds.all.toString()
                tvweatherFeels.text =  "Feels like " +main.feelsLike.toString()

                val url = weather[0].icon
                val icon = "http://openweathermap.org/img/w/${url}.png"

                Glide.with(requireContext())
                    .asBitmap()
                    .load(icon)
                    .into(imgIconweather)

                tvdetailWeatherLoc.setOnClickListener {
                    val intent = Intent(requireContext(), DetailWeatherActivity::class.java)
                    intent.putExtra("lokasi",name)
                    startActivity(intent)
                }

            }
        }
    }

    private fun setWeatherForecast(respon : ForecastResponse){
        val data = respon.list
        for (i in data.indices){
            val date = data[i].dtTxt
            val desc = data[i].weather[0].description
            val temp = data[i].main.temp.toString()

            val tempForecast = Foredata(
                date,
                desc,
                temp
            )

            forecastList.add(tempForecast)
            forecastAdapter = ForecastAdapter(forecastList.distinct())
            val recview = binding.weatherForecast
            recview.adapter = forecastAdapter
            recview.layoutManager = LinearLayoutManager(requireContext())
        }
    }
}