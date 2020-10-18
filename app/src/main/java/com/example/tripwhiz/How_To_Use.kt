package com.example.tripwhiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class How_To_Use : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_how__to__use)
        val SplashScreenTimeOut= 2000
        val feedIntent = Intent(this@How_To_Use,feed_back::class.java)

        Handler().postDelayed(
            {
                startActivity(feedIntent)
                finish()
            },SplashScreenTimeOut.toLong()
        )
    }
}