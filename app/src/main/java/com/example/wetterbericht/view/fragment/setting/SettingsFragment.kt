package com.example.wetterbericht.view.fragment.setting

import android.os.Bundle
import android.util.Log
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.example.wetterbericht.R
import com.example.wetterbericht.util.TaskReminder
import com.google.android.gms.tasks.Task

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.setting_prefrence, rootKey)


        var notify = false
        val notificationPrefrence = findPreference<SwitchPreference>(getString(R.string.pref_key_notify))
        val intervalPrefrence = findPreference<ListPreference>(getString(R.string.pref_key_interval))
        
        notificationPrefrence?.setOnPreferenceChangeListener { _, value ->
            if (value as Boolean) {
                notify = value
                setReminderNotification(1000)
            }else{
                notify = false
                TaskReminder().cancelAlarm(requireContext())
            }
            true
        }

        intervalPrefrence?.setOnPreferenceChangeListener { _, newValue ->
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
    }

    private fun setReminderNotification(interval : Long){
        TaskReminder().setDailyReminder(requireContext(),interval)
    }


}