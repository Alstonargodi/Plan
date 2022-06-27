package com.example.wetterbericht.view.fragment.setting

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.example.wetterbericht.R
import com.example.wetterbericht.util.TaskReminder
import com.google.android.gms.tasks.Task

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.setting_prefrence, rootKey)

        val notificationPrefrence = findPreference<SwitchPreference>(getString(R.string.pref_key_notify))

        notificationPrefrence?.setOnPreferenceChangeListener { prefrence, value ->
            if (value as Boolean) {
                TaskReminder().setDailyReminder(requireContext())
            }else{

            }
            true
        }
    }


}