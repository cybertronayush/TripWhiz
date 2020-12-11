package com.example.tripwhiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.mapbox.mapboxsdk.MapmyIndia
import com.mmi.LicenceManager
import com.mmi.services.account.MapmyIndiaAccountManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        LicenceManager.getInstance().setRestAPIKey("sekw6olgourq7mdosdywzzko6euaku4p")
        LicenceManager.getInstance().setMapSDKKey("sekw6olgourq7mdosdywzzko6euaku4p")
        MapmyIndiaAccountManager.getInstance().restAPIKey = "sekw6olgourq7mdosdywzzko6euaku4p"
        MapmyIndiaAccountManager.getInstance().mapSDKKey = "sekw6olgourq7mdosdywzzko6euaku4p"
        MapmyIndiaAccountManager.getInstance().atlasClientId = "33OkryzDZsLQOhxJM1Z8ZxaQGKEYucoUB2KMCjVYDCu8nSkrbrzpE8EVyHp9VryAwQnpmsBAdm4zjQuuchwnREWIiChyji9bVkzx17zPBrvBjZRNeT2B2w=="
        MapmyIndiaAccountManager.getInstance().atlasClientSecret = "lrFxI-iSEg_ICTc3WCTnqQIEt9OzuuJqJfBlD9z3YlJZDVGB-xgOs42WFWC5eL5Vzv-SQ2ht0z32DoKzWuKFo3dxWv2nfrfrm0z4cPCNHQkusz_nZzv__HaEhTo_iIy9"
        MapmyIndia.getInstance(applicationContext)
    }
}
class FeasibilityReport : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_feasibility)
        //val retry = findViewById<Button>(R.id.button4)
        //retry.setOnClickListener{
          //  val inte=Intent(this,PlanTrip::class.java)
            //startActivity(inte)}
    }
}
class RecommendedPreferences : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommended)
    }
}
class ChangePreferenes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.changepreferences)
    }
}
class Feasibility2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.feasibility2)

        }
    }

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)
    }
}
class Result : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
    }
}