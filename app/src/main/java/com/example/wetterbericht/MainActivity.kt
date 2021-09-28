package com.example.wetterbericht

import android.app.Activity
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.wetterbericht.databinding.ActivityMainBinding
import com.example.wetterbericht.model.APIweather.Main
import com.example.wetterbericht.model.repo.api.mainrepo
import com.example.wetterbericht.model.room.cuaca
import com.example.wetterbericht.viewmodel.api.Mainviewmodel
import com.example.wetterbericht.viewmodel.api.Vmfactory
import com.example.wetterbericht.viewmodel.room.Cuacaviewmodel
import com.google.android.gms.location.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var mbinding : ActivityMainBinding

    //cuaca
    lateinit var cviewmodel : Cuacaviewmodel
    lateinit var mcviewmodel : Mainviewmodel

    //location
    lateinit var locationclien : FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest
    lateinit var locationcallback : LocationCallback
    private var code = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mbinding.root)


        //setup bottomnavmenu
        val navview : BottomNavigationView = mbinding.navmenu
        val navController = findNavController(R.id.hostfragment)
        navview.setupWithNavController(navController)

        //weather by gps
        val repo = mainrepo()
        val vmfactory = Vmfactory(repo)
        mcviewmodel = ViewModelProvider(this,vmfactory).get(Mainviewmodel::class.java)
        cviewmodel = ViewModelProvider(this).get(Cuacaviewmodel::class.java)

        //coroutine location
        locationclien = LocationServices.getFusedLocationProviderClient(this)
        provideloc()
        lifecycleScope.launch {
            withContext(Dispatchers.Main){
                gpsguaranted()
            }
        }
    }

    suspend fun gpsguaranted(){
        delay(900000L) //update setiap 25 menit
        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
           ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
           ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),code)
           }
        locationclien.requestLocationUpdates(locationRequest,locationcallback, Looper.myLooper())
    }

    private fun provideloc(){
        locationRequest = LocationRequest.create().apply {
            interval = 0
            fastestInterval = 0
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            maxWaitTime = 5000
        }
        locationcallback = object : LocationCallback(){
            override fun onLocationResult(p0: LocationResult) {
                val location = p0.locations.get(p0.locations.size-1)
                val lat = location.latitude
                val lon = location.longitude
                Log.d("loc","location now"+lat + lon)
                mcviewmodel.getdatalocation(lat,lon)
            }
        }
        mcviewmodel.datalocationrespon.observe(this, Observer { respons->
            val desc = respons.body()?.weather?.get(0)?.description.toString()
            val url = respons.body()?.weather?.get(0)?.icon
            val urimage = "http://openweathermap.org/img/w/${url}.png"

            val datac = cuaca(
                respons.body()?.name.toString(),
                0,
                desc,
                respons.body()?.main?.temp.toString(),
                respons.body()?.main?.feelsLike.toString(),
                Integer.parseInt(respons.body()?.main?.humidity.toString()),
                urimage,
                respons.body()?.main?.feelsLike.toString(),
                respons.body()?.wind?.speed.toString(),
                respons.body()?.clouds?.all.toString(),
                respons.body()?.visibility.toString(),
                respons.body()?.main?.pressure.toString()
            )
             cviewmodel.add(datac)
        })

        mcviewmodel.datarespon.observe(this, Observer { respons->

        })
    }
}