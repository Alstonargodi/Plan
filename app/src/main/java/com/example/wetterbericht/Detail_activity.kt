package com.example.wetterbericht

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wetterbericht.view.adapter.Recyclerview.Detail.Tododetailsubadapter
import com.example.wetterbericht.view.util.todo.Alarmreceiver
import com.example.wetterbericht.viewmodel.room.todoviewmodel
import kotlinx.android.synthetic.main.activity_detail.*

class Detail_activity : AppCompatActivity() {
    lateinit var tdoviewmodel: todoviewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        Alarmreceiver()

        tdoviewmodel = ViewModelProvider(this).get(todoviewmodel::class.java)

        val nama = intent.getStringExtra("namaact")
        val desc = intent.getStringExtra("desc")
        tvdetail_todoin_nama.setText(nama)
        tvdetail_todoin_desc.setText(desc)

        //adapter
        val adapter = Tododetailsubadapter()
        val recview = recdetail_todoin
        recview.adapter = adapter
        recview.layoutManager = LinearLayoutManager(this)

        tdoviewmodel.selectsub("tugas0")
        tdoviewmodel.readsubtaskin.observe(this, Observer { respon ->
            adapter.setdata(respon)
        })


    }
}