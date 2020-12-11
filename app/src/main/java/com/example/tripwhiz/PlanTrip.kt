package com.example.tripwhiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
import java.io.*

import java.util.*
import kotlin.collections.ArrayList

class PlanTrip : AppCompatActivity() {
    val placelist=ArrayList<String>()
    val TList = ArrayList<Trains>()
    var Tmin:Int = 0
    val Hlist =ArrayList<String>()

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
        val datePicker = findViewById<EditText>(R.id.editTextDate5)

        val endate = findViewById<EditText>(R.id.editTextDate6)

        checkfeas.setOnClickListener {

            // Getting the user input
            val budget = budget.text.toString().toInt()
            val Spoint = from.text.toString()
            val dest = destination.text.toString()
            val memb = members.text.toString().toInt()

            if(budget<12864){
                startActivity(Intent(this,FeasibilityReport::class.java))
            }
            else
                startActivity(Intent(this,FeasibilityReport::class.java))

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
                                getNearBy(place.latitude, place.longitude)
                                getNearHotel(place.latitude, place.longitude)
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
        for(j in listOf("Historical","Zoo","Museum","Architectural","Themepark","Theme park","Theme park/parks","love","Adventure")){
        MapmyIndiaNearby.builder()
                .setLocation(latitude, longitude)
                .keyword(j)
                .radius(10000)
                .build()
                .enqueueCall(object : Callback<NearbyAtlasResponse> {
                    override fun onResponse(call: Call<NearbyAtlasResponse>, response: Response<NearbyAtlasResponse>) {

                        if (response.code() == 200) {
                            if (response.body() != null) {
                                val nearByList = response.body()!!.suggestedLocations
                                 if (nearByList.size > 0) {
                                   for(i in nearByList)
                                placelist.add(i.placeName)
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

                })}}

    private fun getNearHotel(latitude: Double, longitude: Double) {

        MapmyIndiaNearby.builder()
                .setLocation(latitude, longitude)
                .keyword("Hotel")
                .radius(10000)
                .build()
                .enqueueCall(object : Callback<NearbyAtlasResponse> {
                    override fun onResponse(call: Call<NearbyAtlasResponse>, response: Response<NearbyAtlasResponse>) {

                        if (response.code() == 200) {
                            if (response.body() != null) {
                                val nearByList = response.body()!!.suggestedLocations
                                //if (nearByList.size > 0) {
                                // for(i in nearByList)
                                //Toast.makeText(this@PlanTrip, i.placeName, Toast.LENGTH_SHORT).show()
                                //}
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
    class Trains{
        var src: String? =null
        var dstn: String? =null
        var s2: Int = 0
        var Train: String? = null
        var Depart: String? = null
        var days: String? = null
        var sl: Int = 0
        var a3: Int = 0
        var a2: Int = 0
        var a1: Int = 0
        constructor(src: String?,dstn: String?,Train: String?, Depart: String?, days: String?,s2: Int,sl: Int,a3: Int,a2: Int,a1: Int) {
            this.Train = Train
            this.Depart = Depart
            this.s2 = s2
            this.sl = sl
            this.a3 = a3
            this.a2 = a2
            this.src=src
            this.dstn=dstn
            this.days=days
            this.a1=a1
        }


    }

    fun gettrain(source: String, destination: String) {


        try {
            val trains = ArrayList<Trains>()
            trains.add(Trains("mathura","lucknow","09021 MUMBAI BANDRA T - LUCKNOW Covid SF Special","10:25","S",193,303,761,1061,0))
            trains.add(Trains("mathura","lucknow","09451 GANDHIDHAM - BHAGALPUR Festival Special","16:20","F",173,279,716,1061,0))
            trains.add(Trains("mathura","lucknow","04199 GWALIOR - BALRAMPUR Sushasan Covid SF Special","17:02","W",238,388,1006,1411,0))
            trains.add(Trains("mathura","jaipur","02403 PRAYAGRAJ - JAIPUR Covid SF Special","07:35","all",148,233,571,781,1251))
            trains.add(Trains("mathura","udipur","02963 DELHI H NIZAMUDDIN - UDAIPUR CITY Mewar Covid SF Special","21:05 ","all",233,383,986,1386,2281))
            trains.add(Trains("mathura","haridwar","02401 KOTA - DEHRADUN Nanda Devi Covid Special","21:40","all",0,0,671,991,1621))
            trains.add(Trains("mathura","haridwar","09017 MUMBAI BANDRA T - HARIDWAR Festival Special","08:05","W",163,253,671,991,0))
            trains.add(Trains("mathura","dehradun","02401 KOTA - DEHRADUN Nanda Devi Covid Special","21:40","all",0,0,731,1081,1766))
            trains.add(Trains("mathura","ahmedabad","02918 DELHI H NIZAMUDDIN - AHMEDABAD Gujarat Sampark Kranti Exp Covid SPL","15:55","T,Th,Sa",270,450,1185,1675,0))
            trains.add(Trains("mathura","ahmedabad","09452 BHAGALPUR - GANDHIDHAM Festival Special","09:20","M",288,463,1241,1831,0))
            trains.add(Trains("lucknow","mathura","09452 BHAGALPUR - GANDHIDHAM Festival Special","00:05","M",173,279,716,1061,0))
            trains.add(Trains("lucknow","mathura","04200 BALRAMPUR - GWALIOR Sushasan Covid SF Special","16:10","Th",238,388,1006,1411,0))
            trains.add(Trains("lucknow","mathura","09022 LUCKNOW - MUMBAI BANDRA T Covid SF Special","23:35","S",193,303,761,1061,0))
            trains.add(Trains("lucknow","agra","04863 VARANASI - JODHPUR Festival Special","00:15","T,F,S",148,228,601,896,0))
            trains.add(Trains("lucknow","agra","02419 LUCKNOW - NEW DELHI Gomti Exp Covid Special","06:00","all",173,566,946,1531,0))
            trains.add(Trains("lucknow","agra","02003 LUCKNOW - NEW DELHI Swarna Shatabdi Covid Special","15:35","all",0,471,1100,1290,0))
            trains.add(Trains("lucknow","agra","02179 LUCKNOW - AGRA FORT InterCity Covid Special","15:55","all",148,491,601,0,0))
            trains.add(Trains("lucknow","agra","03483 MALDA TOWN - OLD DELHI Farakka Covid Special","19:05","T,W,F,S",153,243,636,946,0))
            trains.add(Trains("lucknow","agra","09038 GORAKHPUR - MUMBAI BANDRA T Avadh Exp Covid Special","20:50","M,W,F,Sa",148,228,601,896,0))
            trains.add(Trains("lucknow","agra","02553 SAHARSA - NEW DELHI Vaishali Exp Covid SF Special","22:18","all",158,253,626,861,1386))
            trains.add(Trains("lucknow","agra","04853 VARANASI - JODHPUR Festival Special","00:15","M,W,Sa",148,228,601,896,0))
            trains.add(Trains("lucknow","agra","05045 GORAKHPUR - OKHA Festival Special","10:30","Th",148,228,601,896,0))
            trains.add(Trains("lucknow","agra","05269 MUZAFFARPUR - AHMEDABAD Special","10:45","Th",148,0,0,0,0))
            trains.add(Trains("lucknow","agra","04200 BALRAMPUR - GWALIOR Sushasan Covid SF Special","16:10","Th",253,408,1051,1481,0))
            trains.add(Trains("lucknow","agra","03413 MALDA TOWN - OLD DELHI Farakka Covid Special (Via Sultanpur)","19:05","M,Th,Sa",153,238,626,931,0))
            trains.add(Trains("lucknow","agra","09040 MUZAFFARPUR - MUMBAI BANDRA T Avadh Exp Covid Special","20:50","T,Th,S",148,228,601,896,0))
            trains.add(Trains("lucknow","delhi","05909 DIBRUGARH - LALGARH Avadh Assam Covid Special","05:40","all",193,298,781,1161,1911))
            trains.add(Trains("lucknow","delhi","02419 LUCKNOW - NEW DELHI Gomti Exp Covid Special","06:00","all",213,0,731,1256,2051))
            trains.add(Trains("lucknow","delhi","02875 PURI - ANAND VIHAR T Neelachal Covid Special","13:45","T,F,S",193,313,841,1241,0))
            trains.add(Trains("lucknow","delhi","02003 LUCKNOW - NEW DELHI Swarna Shatabdi Covid Special","15:35","all",0,0,686,1610,1900))
            trains.add(Trains("lucknow","delhi","02391 RAJGIR - NEW DELHI Shramjeevi Covid SF Specia","20:13","all",208,333,841,1176,1941))
            trains.add(Trains("lucknow","delhi","02565 DARBHANGA - NEW DELHI Bihar Sampark Kranti Exp Covid Special","21:15","all",195,315,815,1200,1885))
            trains.add(Trains("lucknow","delhi","02555 GORAKHPUR - HISAR Gorakhdham Covid SF Special","21:45","all",213,353,896,1256,2051))
            trains.add(Trains("lucknow","delhi","02229 LUCKNOW - NEW DELHI Lucknow Mail Covid Special","22:00","all",193,303,791,1176,1941))
            trains.add(Trains("lucknow","delhi","02553 SAHARSA - NEW DELHI Vaishali Exp Covid SF Special","22:18","all",213,353,896,1256,2051))
            trains.add(Trains("lucknow","jaipur","04863 VARANASI - JODHPUR Festival Special","00:15","T,F,Sa",213,338,901,1331,0))
            trains.add(Trains("lucknow","jaipur","04865 VARANASI - JODHPUR Festival Special","00:15","Th",213,338,901,1331,0))
            trains.add(Trains("lucknow","jaipur","09602 NEW JALPAIGURI - UDAIPUR CITY Festival Special","03:20","M",258,418,1116,1646,0))
            trains.add(Trains("lucknow","jaipur","05269 MUZAFFARPUR - AHMEDABAD Special","10:45","Th",213,0,0,0,0))
            trains.add(Trains("lucknow","udaipur","09602 NEW JALPAIGURI - UDAIPUR CITY Festival Special","03:20","M",333,538,1431,2116,0))
            trains.add(Trains("lucknow","haridwar","02191 JABALPUR - HARIDWAR Festival Special","05:10","W",208,333,841,1176,0))
            trains.add(Trains("lucknow","ahmedabad","09168 VARANASI - AHMEDABAD Sabarmati Covid Special","23:15","T,Th,F,S",343,553,1461,2166,0))
            trains.add(Trains("lucknow","ahmedabad","09166 DARBHANGA - AHMEDABAD Sabarmati Exp Covid Special","23:15","M,W,Sa",343,553,1181,1461,2166))
            trains.add(Trains("lucknow","varanasi","04854 JODHPUR - VARANASI Festival Special","01:30","M,Th,Sa",148,228,601,896,0))
            trains.add(Trains("lucknow","varanasi","03392 NEW DELHI - RAJGIR Clone Special","19:25","all",225,575,0,0,0))
            trains.add(Trains("lucknow","varanasi","02392 NEW DELHI - RAJGIR Shramjeevi Covid SF Special","21:30","all",153,243,571,781,1251))
            trains.add(Trains("lucknow","varanasi","02506 NEW DELHI - DIBRUGARH Covid Rajdhani Special","17:35","Th,S",0,0,816,1046,1621))
            trains.add(Trains("lucknow","varanasi","09167 AHMEDABAD - VARANASI Sabarmati Exp Covid Special","01:05","M,T,Th,Sa",148,228,601,896,0))
            trains.add(Trains("delhi","lucknow","02004 NEW DELHI - LUCKNOW Swarna Shatabdi Covid Special","06:10","all",0,0,686,1300,1900))
            trains.add(Trains("delhi","lucknow","05910 LALGARH - DIBRUGARH Avadh Assam Covid Special","07:50","all",193,298,781,1161,1911))
            trains.add(Trains("delhi","lucknow","02420 NEW DELHI - LUCKNOW Gomti SF Exp Covid Special","12:25","all",213,0,731,1256,2051))
            trains.add(Trains("delhi","lucknow","02392 NEW DELHI - RAJGIR Shramjeevi Covid SF Special","13:10","all",208,333,841,1176,1941))
            trains.add(Trains("delhi","lucknow","05116 OLD DELHI - CHHAPRA Festival Special","14:00","S",193,313,841,1241,0))
            trains.add(Trains("delhi","lucknow","02564 NEW DELHI - SAHARSA Humsafar Covid Special","17:55","all",298,781,0,0,0))
            trains.add(Trains("delhi","jaipur","02422 JAMMU TAWI - AJMER Festival SF Special","04:25","all",158,253,626,861,1386))
            trains.add(Trains("delhi","jaipur","05314 RAMNAGAR - JAISALMER Corbett Park Covid Special","04:30","all",143,223,581,861,0))
            trains.add(Trains("delhi","jaipur","04321 BAREILLY - BHUJ Festival Special","11:50","M,W,F,S",143,223,581,861,0))
            trains.add(Trains("delhi","jaipur","02916 OLD DELHI - AHMEDABAD Ashram Exp Covid Special","15:20","all",158,253,626,861,1386))
            trains.add(Trains("delhi","jaipur","02958 NEW DELHI - AHMEDABAD Rajdhani (Covid SPL AC)","19:55","all",0,0,841,1096,1736))
            trains.add(Trains("delhi","udaipur","02963 DELHI H NIZAMUDDIN - UDAIPUR CITY Mewar Covid SF Special","19:00","all",263,433,1111,1571,2606))
            trains.add(Trains("delhi","udaipur","09610 HARIDWAR - UDAIPUR CITY Covid Special","00:30","T,F,S",253,403,1076,1586,0))
            trains.add(Trains("delhi","udaipur","09602 NEW JALPAIGURI - UDAIPUR CITY Festival Special","12:30","M",248,403,1066,1571,0))
            trains.add(Trains("delhi","haridwar","09609 UDAIPUR CITY - HARIDWAR Covid Special","03:45","M,Th,Sa",123,193,521,781,0))
            trains.add(Trains("delhi","haridwar","02055 NEW DELHI - DEHRADUN Janshatabdi Exp Covid Special","15:20","all",143,0,471,0,0))
            trains.add(Trains("delhi","haridwar","04041 OLD DELHI - DEHRADUN Festival Special","22:25","all",133,213,521,781,1251))
            trains.add(Trains("delhi","haridwar","02401 KOTA - DEHRADUN Nanda Devi Covid Special","23:45","all",0,0,521,781,1251))
            trains.add(Trains("delhi","ahmedabad","02916 OLD DELHI - AHMEDABAD Ashram Exp Covid Special","15:20","all",303,493,1276,1821,3041))
            trains.add(Trains("delhi","ahmedabad","02958 NEW DELHI - AHMEDABAD Rajdhani (Covid SPL AC)","19:55","all",0,0,1671,2271,3731))
            trains.add(Trains("delhi","ahmedabad","02918 DELHI H NIZAMUDDIN - AHMEDABAD Gujarat Sampark Kranti Exp Covid SPL","13:55","T,Th,S",320,535,1400,1990,0))
            trains.add(Trains("delhi","ahmedabad","09416 OLD DELHI - AHMEDABAD Clone Covid Special","14:20","M,T",463,0,1231,0,0))
            trains.add(Trains("delhi","varanasi","02436 NEW DELHI - VARANASI Vande Bharat Covid Special","06:00","all",0,0,881,0,2645))
            trains.add(Trains("delhi","varanasi","02876 ANAND VIHAR T - PURI Neelachal Covid Special","07:30","T,F,S",263,423,1121,1661,0))
            trains.add(Trains("delhi","varanasi","02506 NEW DELHI - DIBRUGARH Covid Rajdhani Special","9:25","Th,S",0,0,1611,2121,3416))
            trains.add(Trains("delhi","varanasi","03392 NEW DELHI - RAJGIR Clone Special","11:00","all",0,423,1121,0,0))
            trains.add(Trains("delhi","varanasi","02392 NEW DELHI - RAJGIR Shramjeevi Covid SF Special","13:10","all",273,443,1146,1621,2696))
            trains.add(Trains("delhi","varanasi","04006 ANAND VIHAR T - SITAMARHI Lichchhavi Covid Special","18:00","all",253,403,1076,1586,0))
            trains.add(Trains("delhi","dehradun","02055 NEW DELHI - DEHRADUN Janshatabdi Exp Covid Special","15:20","all",158,0,521,0,0))
            trains.add(Trains("delhi","dehradun","04041 OLD DELHI - DEHRADUN Festival Special","22:25","all",153,233,611,911,1471))
            trains.add(Trains("delhi","dehradun","02401 KOTA - DEHRADUN Nanda Devi Covid Special","23:45","all",0,0,581,861,1386))


            for (i in trains) {
                if (i.src==source && i.dstn==destination)
                    if(i.a3!=0){
                    TList.add(i)}
                if(i.s2!=0 && i.s2>Tmin)
                    Tmin=i.s2


            }
            /*while (line != null) {
                val tokens = line.split(",")
                if (tokens.size > 0) {
                    if (tokens[Sname].split("")[0]==source && c==0 ){
                        todi=0
                        c=c+1
                        ttrain=tokens[Tname]
                    }
                    else if (tokens[Tname]!=ttrain && c==1){
                        c=0
                        todi=0
                    }
                    else if (tokens[Sname].split("")[0]==destination && c==1 && ttrain == tokens[Tname]) {
                        c=0
                        var tr = Trains(todi, ttrain, tokens[Dtime])
                        trains.add(tr)
                        todi=0
                    }
                }
                todi = todi + Integer.parseInt(tokens[dist])*/


        } catch (e: Exception) {

            e.printStackTrace()
        }
    }

}
