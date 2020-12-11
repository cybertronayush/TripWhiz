package `in`.ayushsingh.backendrestaurant.room

import `in`.ayushsingh.backendrestaurant.modal.LocationSuggestion
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DaoCities {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCity(city: LocationSuggestion);

    @Query("select * from LocationSuggestion order by name")
    fun getCitiesSearched(): List<LocationSuggestion>



}