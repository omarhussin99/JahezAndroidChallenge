package net.jahez.jahezchallenge.feature_restaurant.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.jahez.jahezchallenge.core.util.Resource
import net.jahez.jahezchallenge.feature_restaurant.domain.model.Restaurant
import net.jahez.jahezchallenge.feature_restaurant.domain.repository.RestaurantRepository


class FakeRestaurantRepository : RestaurantRepository {

    private val restaurants = mutableListOf<Restaurant>()

    override fun getRestaurants(): Flow<Resource<List<Restaurant>>> {
        TODO("Not yet implemented")
    }

    override fun getStoredRestaurants(): Flow<List<Restaurant>> {
        return flow { emit(restaurants) }
    }

    fun insertRestaurants(restaurants:List<Restaurant>){
        this.restaurants.addAll(restaurants)
    }

}