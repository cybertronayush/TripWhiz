package com.example.ayushsinghchauhantripwhiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        getSupportActionBar()?.hide()



        val SplashScreenTimeOut= 25000
        val dashIntent = Intent(this@SplashScreen, details_Page::class.java)

        Handler().postDelayed(
            {
                startActivity(dashIntent)
                finish()
            },SplashScreenTimeOut.toLong()
        )
    }
}