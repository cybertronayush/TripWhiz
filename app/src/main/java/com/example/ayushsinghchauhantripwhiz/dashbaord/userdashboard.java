package com.example.ayushsinghchauhantripwhiz.dashbaord;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;

public class userdashboard extends AppCompatActivity {

    RecyclerView featuredRecycler, mostViewedRecycler, categoriesRecycler;
    RecyclerView.Adapter adapter;
    private GradientDrawable gradient1, gradient2, gradient3, gradient4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_dashboard);

        //Hooks
        featuredRecycler = findViewById(R.id.featured_recycler);

        //Functions will be executed automatically when this activity will be created
        featuredRecycler();


    }
    private void featuredRecycler() {

        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();

        featuredLocations.add(new FeaturedHelperClass(R.drawable.mcdonald_img, "Trip To Rishikesh", "These are the Best Planned Trips, This section conatains best  smart Pre-planned Trip by Our app'sReccomendation Engine Which gets smarter According to your uses."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.lucknow, "Trip To Lucknow", "Lucknow is the capital city of the Indian state of Uttar Pradesh, and is also the administrative headquarters of the eponymous district and division,where you'll find great authentic dish and Great culture"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.manali, "Trip To Manali", "Besides natural charm and unparalleled beauty, Manali is known for its unlimited adventure opportunities, the famous Hadimba Temple, the scenic Rohtang Pass, the snow-laden Solang Valley and its delightful culinary scene.."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.udaipur, "Trip To  Udaipur", "Udaipur is famous for its pristine beauty and royal architecture like forts and palaces. The Sajjan Garh fort is the best place for sunset view and the City Palace is a must-visit."));


        adapter = new FeaturedAdpater(featuredLocations);
        featuredRecycler.setAdapter(adapter);


    }
}