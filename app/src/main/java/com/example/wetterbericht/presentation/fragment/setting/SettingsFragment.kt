package com.example.wetterbericht.presentation.fragment.setting

import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.example.wetterbericht.R
import com.example.wetterbericht.helpers.taskreminder.TaskReminder
import com.example.wetterbericht.helpers.weatherupdate.WeatherUpdate

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
                setReminderNotification(1000)
            }else{
                notify = false
                TaskReminder().cancelAlarm(requireContext())
            }
            true
        }

        intervalPreference?.setOnPreferenceChangeListener { _, newValue ->
            if (notify){
                when(newValue as String){
                    "10 minutes"->{
                        setReminderNotification(600000)
                    }
                    "15 minutes"->{
                        setReminderNotification(1200000)
                    }
                    "25 minutes"->{
                        setReminderNotification(1500000)
                    }
                    "30 minutes"->{
                        setReminderNotification(1800000)
                    }
                    "60 minutes"->{
                        setReminderNotification(3600000)
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

    private fun setReminderNotification(interval : Long){
        TaskReminder().setDailyReminder(requireContext(),interval)
    }


}