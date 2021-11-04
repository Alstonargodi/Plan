package com.example.wetterbericht.view.Login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wetterbericht.MainActivity
import com.example.wetterbericht.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signupactivity.*

class Signupactivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signupactivity)

        btn_signup.setOnClickListener {
            signup()
        }
    }

    private fun signup(){
        val setemail = et_email_signup.text.toString()
        val setpass = et_pass_signup.text.toString()

        if (setemail == "" || setpass == ""){
            Toast.makeText(this,"Form is empty please fill out",Toast.LENGTH_SHORT).show()
        }else{
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(setemail,setpass).addOnSuccessListener { sukses ->

                Toast.makeText(this,"Hi welcome ${setemail}",Toast.LENGTH_SHORT).show()

                Log.d("email",setemail)
                startActivity(Intent(this,MainActivity::class.java))
            }
        }
    }
}