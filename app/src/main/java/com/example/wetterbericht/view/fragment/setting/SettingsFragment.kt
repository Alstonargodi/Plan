package com.example.wetterbericht.view.fragment.setting

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.wetterbericht.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.setting_prefrence, rootKey)
    }
}