package com.example.wetterbericht

import android.app.*
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.wetterbericht.databinding.ActivityMainBinding
import com.example.wetterbericht.model.APIweather.Main
import com.example.wetterbericht.model.repo.api.mainrepo
import com.example.wetterbericht.model.room.cuaca
import com.example.wetterbericht.view.receiver.alarmreceiver
import com.example.wetterbericht.view.util.todo.Alarmreceiver
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
import okhttp3.internal.notify
import kotlin.math.log

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

    //notif
    val name = "todo alarm"
    val desc = "test"
    val idnotif = "alarmid"
    val notifid = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab_add.setOnClickListener {
            startActivity(Intent(this,Todo_add::class.java))
        }


        //setup bottomnavmenu
        val navview : BottomNavigationView = navmenu
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
        lifecycleScope.launch {
            withContext(Dispatchers.Main){
                updateweather()
            }
        }

        //notif
        val context = this
        Alarmreceiver()

        //alarm
        setalarm()
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
    }

    //update data
    suspend fun updateweather(){
        delay(900000L)
        cviewmodel.readdata.observe(this, Observer { responsa->
            for(i in 0 until responsa.size){
                val nama = responsa[i].loc
                Log.d("test",nama)
                mcviewmodel.getdata(nama)
            }
            mcviewmodel.datarespon.observe(this, Observer {
                val desc = it.body()?.weather?.get(0)?.description.toString()
                val url = it.body()?.weather?.get(0)?.icon
                val urimage = "http://openweathermap.org/img/w/${url}.png"

                val dataup = cuaca(
                    it.body()?.name.toString(),
                    0,
                    desc,
                    it.body()?.main?.temp.toString(),
                    it.body()?.main?.feelsLike.toString(),
                    Integer.parseInt(it.body()?.main?.humidity.toString()),
                    urimage,
                    it.body()?.main?.feelsLike.toString(),
                    it.body()?.wind?.speed.toString(),
                    it.body()?.clouds?.all.toString(),
                    it.body()?.visibility.toString(),
                    it.body()?.main?.pressure.toString()
                )
                cviewmodel.add(dataup)
            })
        })
    }

    fun setalarm(){
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

        val intent = Intent(this,Alarmreceiver::class.java)
        val pendingintent = PendingIntent.getBroadcast(this,0,intent,0)
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            200,                             //triger tanggaldata
            AlarmManager.INTERVAL_DAY,pendingintent
            //interval
        )

        Toast.makeText(this,"test",Toast.LENGTH_SHORT).show()
    }



}