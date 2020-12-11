package com.example.ayushsinghchauhantripwhiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class details_Page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details__page)
        val SplashScreenTimeOut= 3000
        val prevIntent = Intent(this@details_Page, Previous_Trips::class.java)

        Handler().postDelayed(
            {
                startActivity(prevIntent)

                finish()
            },SplashScreenTimeOut.toLong()
        )
    }
}