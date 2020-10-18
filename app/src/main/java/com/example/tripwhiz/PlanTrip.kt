package com.example.tripwhiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class PlanTrip : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plan_trip)

//Here i will writes those OnclickListner's when i'll work on backend
            //for testing i am just using sleep try catch here ..........
        val SplashScreenTimeOut= 2000
        val howIntent = Intent(this@PlanTrip,How_To_Use::class.java)

        Handler().postDelayed(
            {
                startActivity(howIntent)
                finish()
            },SplashScreenTimeOut.toLong()
        )
    }
}