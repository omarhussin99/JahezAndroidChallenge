package net.jahez.jahezchallenge.feature_home.data.remote

import net.jahez.jahezchallenge.feature_home.data.dto.RestaurantDto
import retrofit2.http.GET

interface ApiServices {

    @GET("restaurants.json")
    suspend fun getRestaurants():List<RestaurantDto>
}