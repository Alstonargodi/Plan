package com.example.wetterbericht.view.Login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wetterbericht.MainActivity
import com.example.wetterbericht.R
import com.google.firebase.auth.FirebaseAuth
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

        btn_enter.setOnClickListener {
            signin()
        }
    }

    private fun signin(){
        val email = et_username.text.toString()
        val password = et_password.text.toString()

        if (email.isNotEmpty() || password.isNotEmpty()){
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener { result->
                if (result.isSuccessful){
                    Toast.makeText(this,"welcome back ! ${email}", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,MainActivity::class.java))
                }else {
                    val error = result.exception.toString()
                    Toast.makeText(this,"sorry ${error}", Toast.LENGTH_SHORT).show()
                }
            }
        }else{
            Toast.makeText(this,"Form is empty please fill out", Toast.LENGTH_SHORT).show()
        }

    }
}