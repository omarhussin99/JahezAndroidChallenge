package net.jahez.jahezchallenge.feature_home.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import net.jahez.jahezchallenge.feature_home.data.local.entity.RestaurantEntity

@Dao
interface RestaurantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRestaurants(restaurants: List<RestaurantEntity>)

    @Query("SELECT * FROM restaurants")
    suspend fun getAllRestaurants(): List<RestaurantEntity>

    @Query("DELETE FROM restaurants")
    suspend fun deleteAllRestaurants()

}