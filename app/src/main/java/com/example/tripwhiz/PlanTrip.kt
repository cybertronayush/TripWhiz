package com.example.tripwhiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class PlanTrip : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plan_trip)

        val SplashScreenTimeOut= 1000
        val howIntent = Intent(this@PlanTrip,Help::class.java)

        Handler().postDelayed(
            {
                startActivity(howIntent)

                finish()
            },SplashScreenTimeOut.toLong()
        )
    }
}