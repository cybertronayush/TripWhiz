package com.example.tripwhiz


import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class Previous_Trips : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_previous__trips)
        val SplashScreenTimeOut= 1000
        val DetIntent = Intent(this@Previous_Trips,details_Page::class.java)

        Handler().postDelayed(
            {
                startActivity(DetIntent)

                finish()
            },SplashScreenTimeOut.toLong()
        )
    }
}