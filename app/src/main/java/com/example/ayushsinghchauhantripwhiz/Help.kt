package com.example.ayushsinghchauhantripwhiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class Help : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)
        val SplashScreenTimeOut= 1000
        val nedIntent = Intent(this@Help, backfeed::class.java)

        Handler().postDelayed(
                {
                    startActivity(nedIntent)

                    finish()
                },SplashScreenTimeOut.toLong()
        )
    }
}