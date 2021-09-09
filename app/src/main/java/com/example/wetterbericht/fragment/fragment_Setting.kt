package com.example.wetterbericht.fragment

import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.wetterbericht.R
import com.example.wetterbericht.model.room.cuaca
import com.example.wetterbericht.repo.api.mainrepo
import com.example.wetterbericht.viewmodel.api.Mainviewmodel
import com.example.wetterbericht.viewmodel.api.Vmfactory
import com.example.wetterbericht.viewmodel.room.Cuacaviewmodel
import com.example.wetterbericht.viewmodel.room.todoviewmodel
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.fragment_addweather.*
import kotlinx.android.synthetic.main.fragment_setting.*
import kotlinx.android.synthetic.main.fragment_setting.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_Setting.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_Setting : Fragment() {
    lateinit var mviewmodel : todoviewmodel
    lateinit var mapiviewmodel : Mainviewmodel
    lateinit var mroomviewmodel : Cuacaviewmodel

    //location
    lateinit var localclient : FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest
    lateinit var locationCallback: LocationCallback
    private var reqcode = 100


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_setting, container, false)
        mviewmodel = ViewModelProvider(this).get(todoviewmodel::class.java)

        //weather
        val repo = mainrepo()
        val vmfactory = Vmfactory(repo)
        mapiviewmodel = ViewModelProvider(this,vmfactory).get(Mainviewmodel::class.java) //set
        mroomviewmodel = ViewModelProvider(this).get(Cuacaviewmodel::class.java)

        //Weather based on gps
        localclient = LocationServices.getFusedLocationProviderClient(requireContext())
        weathearlocation()
        view.onoff.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                gpsgranted()
            }else{
                Toast.makeText(context,"GPS off",Toast.LENGTH_SHORT).show()
            }
        }

        //delete todo data
        view.btn_deletetodo.setOnClickListener {
            val alert = AlertDialog.Builder(requireContext())
            alert.setPositiveButton("yes"){_,_ ->
                mviewmodel.deleteall()
                Toast.makeText(requireContext(),"delete complete",Toast.LENGTH_SHORT).show()
            }
            alert.setNegativeButton("no"){_,_ ->}
            alert.setTitle("Are u sure want to delete all?")
            alert.setMessage("choose")
            alert.create().show()
        }


        view.btn_deletecuaca.setOnClickListener {
            val alert = AlertDialog.Builder(requireContext())
            alert.setPositiveButton("yes"){_,_ ->
                mroomviewmodel.delete()
                Toast.makeText(requireContext(),"delete complete",Toast.LENGTH_SHORT).show()
            }
            alert.setNegativeButton("no"){_,_ ->}
            alert.setTitle("Are u sure want to delete all?")
            alert.setMessage("choose")
            alert.create().show()
        }
        return view
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private fun gpsgranted(){
        if(ActivityCompat.checkSelfPermission(requireActivity(),android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(requireActivity(),android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), reqcode)
        }
        localclient.requestLocationUpdates(locationRequest,locationCallback, Looper.myLooper())
    }

    private fun weathearlocation(){
        locationRequest = LocationRequest.create().apply {
            interval = 1000
            fastestInterval = 2000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            maxWaitTime = 200
        }
        locationCallback = object : LocationCallback(){
            override fun onLocationResult(p0: LocationResult) {
                val location = p0.locations.get(p0.locations.size-1)
                val lat = location.latitude
                val lon = location.longitude
                tv_loc.setText(lat.toString())
                mapiviewmodel.getdatalocation(lat,lon)
            }
        }
        mapiviewmodel.datalocationrespon.observe(viewLifecycleOwner, Observer { response ->
                val desc = response.body()?.weather?.get(0)?.description.toString()
                val url = response.body()?.weather?.get(0)?.icon
                val urlimage = "http://openweathermap.org/img/w/${url}.png"

                val inputdua = cuaca(0,
                    response.body()?.name.toString(),
                    desc,
                    response.body()?.main?.temp.toString(),
                    response.body()?.main?.feelsLike.toString(),
                    Integer.parseInt(response.body()?.main?.humidity.toString()),
                    urlimage,

                    response.body()?.main?.feelsLike.toString(),
                    response.body()?.wind?.speed.toString(),
                    response.body()?.clouds?.all.toString(),
                    response.body()?.visibility.toString(),
                    response.body()?.main?.pressure.toString()
                )
                mroomviewmodel.add(inputdua)
        })
    }

}