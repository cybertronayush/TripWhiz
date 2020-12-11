package com.example.tripwhiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import com.mapbox.mapboxsdk.MapmyIndia
import com.mmi.LicenceManager
import com.mmi.services.account.MapmyIndiaAccountManager
import com.mmi.services.api.geocoding.GeoCodeResponse
import com.mmi.services.api.geocoding.MapmyIndiaGeoCoding
import com.mmi.services.api.nearby.MapmyIndiaNearby
import com.mmi.services.api.nearby.model.NearbyAtlasResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class PlanTrip : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        LicenceManager.getInstance().setRestAPIKey("sekw6olgourq7mdosdywzzko6euaku4p")
        LicenceManager.getInstance().setMapSDKKey("sekw6olgourq7mdosdywzzko6euaku4p")
        MapmyIndiaAccountManager.getInstance().restAPIKey = "sekw6olgourq7mdosdywzzko6euaku4p"
        MapmyIndiaAccountManager.getInstance().mapSDKKey = "sekw6olgourq7mdosdywzzko6euaku4p"
        MapmyIndiaAccountManager.getInstance().atlasGrantType="client_credentials"
        MapmyIndiaAccountManager.getInstance().atlasClientId = "33OkryzDZsLQOhxJM1Z8ZxaQGKEYucoUB2KMCjVYDCu8nSkrbrzpE8EVyHp9VryAwQnpmsBAdm4zjQuuchwnREWIiChyji9bVkzx17zPBrvBjZRNeT2B2w=="
        MapmyIndiaAccountManager.getInstance().atlasClientSecret = "lrFxI-iSEg_ICTc3WCTnqQIEt9OzuuJqJfBlD9z3YlJZDVGB-xgOs42WFWC5eL5Vzv-SQ2ht0z32DoKzWuKFo3dxWv2nfrfrm0z4cPCNHQkusz_nZzv__HaEhTo_iIy9"
        MapmyIndia.getInstance(applicationContext)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plan_trip)



        val checkfeas = findViewById<Button>(R.id.button3)
        val budget = findViewById<EditText>(R.id.textView)
        val from = findViewById<EditText>(R.id.textView7)
        val destination = findViewById<EditText>(R.id.textView8)
        val members = findViewById<EditText>(R.id.textView11)
        val start = findViewById<DatePicker>(R.id.textView10)
        val endate = findViewById<DatePicker>(R.id.textView3)
        checkfeas.setOnClickListener {

            // Getting the user input
            val budget = budget.text.toString().toInt()
            val Spoint = from.text.toString()
            val dest = destination.text.toString()
            val memb = members.text.toString().toInt()


            getGeoCode(dest)
    }}
    private fun getGeoCode(geocodeText: String) {
        MapmyIndiaGeoCoding.builder()
            .setAddress(geocodeText)
            .build().enqueueCall(object : Callback<GeoCodeResponse> {
                override fun onResponse(call: Call<GeoCodeResponse>, response: Response<GeoCodeResponse>) {
                    if (response.code() == 200) {
                        if (response.body() != null) {
                            val placesList = response.body()!!.results
                            val place = placesList[0]
                            val add = "Latitude: " + place.latitude + " longitude: " + place.longitude
                            getNearBy(place.latitude,place.longitude)
                            Toast.makeText(this@PlanTrip, add, Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this@PlanTrip, "Not able to get value, Try again.", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this@PlanTrip, response.message(), Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<GeoCodeResponse>, t: Throwable) {
                    Toast.makeText(this@PlanTrip, t.toString(), Toast.LENGTH_SHORT).show()
                }
            })}
    private fun getNearBy(latitude: Double, longitude: Double) {

        MapmyIndiaNearby.builder()
            .setLocation(latitude, longitude)
            .keyword("restaurant")
            .build()
            .enqueueCall(object : Callback<NearbyAtlasResponse> {
                override fun onResponse(call: Call<NearbyAtlasResponse>, response: Response<NearbyAtlasResponse>) {

                    if (response.code() == 200) {
                        if (response.body() != null) {
                            val nearByList = response.body()!!.suggestedLocations
                            if (nearByList.size > 0) {
                                for(i in nearByList)
                                Toast.makeText(this@PlanTrip, i.placeName, Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(this@PlanTrip, "Not able to get value, Try again.", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this@PlanTrip, response.message(), Toast.LENGTH_LONG).show()
                    }
                }
                override fun onFailure(call: Call<NearbyAtlasResponse>, t: Throwable) {
                    Toast.makeText(this@PlanTrip, t.toString(), Toast.LENGTH_SHORT).show()
                }

            })
    }
}
