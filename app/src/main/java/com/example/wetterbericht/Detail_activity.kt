package com.example.wetterbericht

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*

class Detail_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val nama = intent.getStringExtra("namaact")
        val desc = intent.getStringExtra("desc")
        tvdetail_todoin_nama.setText(nama)
        tvdetail_todoin_desc.setText(desc)


    }
}