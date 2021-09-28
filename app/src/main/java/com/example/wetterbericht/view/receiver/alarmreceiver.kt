package com.example.wetterbericht.view.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.wetterbericht.view.fragment_todo

class alarmreceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        val inten = Intent(context,fragment_todo::class.java)

    }
}