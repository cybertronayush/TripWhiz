package com.example.tripwhiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        getSupportActionBar()?.hide()

        val topanimation = AnimationUtils.loadAnimation(this, R.anim.top_animation)



        val SplashScreenTimeOut= 1000
        val dashIntent = Intent(this@SplashScreen,PlanTrip::class.java)

        Handler().postDelayed(
            {
                startActivity(dashIntent)
                finish()
            },SplashScreenTimeOut.toLong()
        )
    }
}