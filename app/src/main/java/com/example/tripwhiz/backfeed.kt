package com.example.tripwhiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class backfeed : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_backfeed)
        val SplashScreenTimeOut= 1000
        val prevIntent = Intent(this@backfeed,Previous_Trips::class.java)

        Handler().postDelayed(
            {
                startActivity(prevIntent)

                finish()
            },SplashScreenTimeOut.toLong()
        )
    }
}