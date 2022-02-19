package net.jahez.jahezchallenge.feature_home.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import net.jahez.jahezchallenge.feature_home.data.local.entity.RestaurantEntity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RestaurantsDatabaseTest : TestCase(){

    private lateinit var db:RestaurantsDatabase
    private lateinit var dao:RestaurantDao

    @Before
    public override fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            RestaurantsDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = db.dao
    }

    @After
    fun closeDB(){
        db.close()
    }

    @Test
    fun insertAndReadRestaurant() =  runBlocking {
        val restaurants : ArrayList<RestaurantEntity> = ArrayList()
        val restaurant = RestaurantEntity(1,"test",1.0,true,"1","test","test",1.0)
        restaurants.add(restaurant)

        dao.insertRestaurants(restaurants)

        assertThat(dao.getAllRestaurants().contains(restaurant)).isTrue()
    }

    @Test
    fun deleteRestaurants() =  runBlocking {
        val restaurants : ArrayList<RestaurantEntity> = ArrayList()
        val restaurant = RestaurantEntity(1,"test",1.0,true,"1","test","test",1.0)
        restaurants.add(restaurant)

        dao.insertRestaurants(restaurants)

        dao.deleteAllRestaurants()

        assertThat(dao.getAllRestaurants().isEmpty()).isTrue()
    }
}