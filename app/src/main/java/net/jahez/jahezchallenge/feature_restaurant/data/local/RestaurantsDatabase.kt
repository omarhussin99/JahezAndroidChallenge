package net.jahez.jahezchallenge.feature_restaurant.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import net.jahez.jahezchallenge.feature_restaurant.data.local.entity.RestaurantEntity

@Database(entities = [RestaurantEntity::class], version = 1, exportSchema = false)
abstract class RestaurantsDatabase : RoomDatabase() {
    abstract val dao:RestaurantDao
}