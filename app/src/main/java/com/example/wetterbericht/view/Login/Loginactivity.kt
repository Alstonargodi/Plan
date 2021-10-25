package com.example.wetterbericht.view.Login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wetterbericht.MainActivity
import com.example.wetterbericht.R
import kotlinx.android.synthetic.main.activity_loginactivity.*

class Loginactivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginactivity)

        btn_visit.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        tv_singup.setOnClickListener {
            startActivity(Intent(this, Signupactivity::class.java))
        }


    }
}