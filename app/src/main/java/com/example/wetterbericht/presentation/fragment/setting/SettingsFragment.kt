package com.example.wetterbericht.presentation.fragment.setting

import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import androidx.work.Data
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.wetterbericht.R
import com.example.wetterbericht.helpers.taskreminder.TaskReminderWorkManager
import com.example.wetterbericht.helpers.taskreminder.TaskReminderWorkManager.Companion.NOTIFICATION_Channel_ID
import com.example.wetterbericht.helpers.weatherupdate.WeatherUpdate
import java.util.concurrent.TimeUnit

class SettingsFragment : PreferenceFragmentCompat() {


    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.setting_prefrence, rootKey)

        var notify = false
        val notificationPreference = findPreference<SwitchPreference>(getString(R.string.pref_key_notify))
        val intervalPreference = findPreference<ListPreference>(getString(R.string.pref_key_interval))
        val weatherPreference = findPreference<SwitchPreference>(getString(R.string.pref_update_weather))

        notificationPreference?.setOnPreferenceChangeListener { _, value ->

            if (value as Boolean) {
                notify = value
                setIntervalTime(1000,true)
            }else{
                notify = false
                setIntervalTime(0,false)
            }
            true
        }

        intervalPreference?.setOnPreferenceChangeListener { _, newValue ->
            if (notify){
                when(newValue as String){
                    "10 minutes"->{
                        setIntervalTime(600000,true)
                    }
                    "15 minutes"->{
                        setIntervalTime(1200000,true)
                    }
                    "25 minutes"->{
                        setIntervalTime(1500000,true)
                    }
                    "30 minutes"->{
                        setIntervalTime(1800000,true)
                    }
                    "60 minutes"->{
                        setIntervalTime(3600000,true)
                    }
                }
            }
            true
        }

        weatherPreference?.setOnPreferenceChangeListener { _, value ->
            if (value as Boolean) {
                WeatherUpdate().setDailyUpdate(requireContext())
            }else{
                WeatherUpdate().cancelUpdateWeather(requireContext())
            }
            true
        }
    }


    private fun setIntervalTime(intervalTime : Long, activated : Boolean){
        val workManager = WorkManager.getInstance(requireContext())
        val notificationBuilder = Data.Builder()
            .putString(NOTIFICATION_Channel_ID,"TaskReminder")
            .build()
        val periodicAlarm = PeriodicWorkRequest.Builder(
            TaskReminderWorkManager::class.java,
            intervalTime,
            TimeUnit.MILLISECONDS
        ).setInputData(notificationBuilder).build()

        if(activated){
            workManager.enqueue(periodicAlarm)
        }else{
            workManager.pruneWork()
            workManager.cancelAllWork()
        }
    }
}