package gla.ac.`in`.miniproject1.tripwhiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner

class Preferences : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)

        val spinner: Spinner = findViewById(R.id.hotels_spinners)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.hotels_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        val spinnerRating: Spinner = findViewById(R.id.hotels_rating_spinners)
        val spinnerRatingRestaurant: Spinner = findViewById(R.id.restaurant_rating_spinners)
        ArrayAdapter.createFromResource(
            this,
            R.array.rating_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinnerRating.adapter = adapter
            spinnerRatingRestaurant.adapter = adapter
        }

        val spinnerFoodType: Spinner = findViewById(R.id.food_type_spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.food_type_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinnerFoodType.adapter = adapter
        }

        val spinnerTravelMode: Spinner = findViewById(R.id.travel_mode_spinners)
        ArrayAdapter.createFromResource(
            this,
            R.array.travel_mode_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinnerTravelMode.adapter = adapter
        }

        val spinnerTravelComfort: Spinner = findViewById(R.id.travel_comfort_spinners)
        ArrayAdapter.createFromResource(
            this,
            R.array.travel_comfort_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinnerTravelComfort.adapter = adapter
        }

        val spinnerPlaces: Spinner = findViewById(R.id.sites_spinners)
        ArrayAdapter.createFromResource(
            this,
            R.array.sites_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinnerPlaces.adapter = adapter
        }
    }
}