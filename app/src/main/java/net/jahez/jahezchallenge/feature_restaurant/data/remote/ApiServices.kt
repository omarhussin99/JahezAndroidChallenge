package net.jahez.jahezchallenge.feature_restaurant.data.remote

import net.jahez.jahezchallenge.feature_restaurant.data.remote.dto.RestaurantDto
import retrofit2.http.GET

interface ApiServices {

    @GET("restaurants.json")
    suspend fun getRestaurants():List<RestaurantDto>
}