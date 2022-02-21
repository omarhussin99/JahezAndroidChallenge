package net.jahez.jahezchallenge.feature_restaurant.domain.repository

import kotlinx.coroutines.flow.Flow
import net.jahez.jahezchallenge.core.util.Resource
import net.jahez.jahezchallenge.feature_restaurant.domain.model.Restaurant

interface RestaurantRepository {
    fun getRestaurants():Flow<Resource<List<Restaurant>>>
    fun getStoredRestaurants():Flow<List<Restaurant>>
}