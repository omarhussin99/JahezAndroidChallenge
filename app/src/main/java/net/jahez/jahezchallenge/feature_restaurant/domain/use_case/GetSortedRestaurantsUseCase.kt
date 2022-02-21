package net.jahez.jahezchallenge.feature_restaurant.domain.use_case

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import net.jahez.jahezchallenge.feature_restaurant.domain.model.Restaurant
import net.jahez.jahezchallenge.feature_restaurant.domain.model.RestaurantOrder
import net.jahez.jahezchallenge.feature_restaurant.domain.repository.RestaurantRepository
import javax.inject.Inject

class GetSortedRestaurantsUseCase @Inject constructor(
    private val repository: RestaurantRepository
    ) {
    operator fun invoke(restaurantOrder: RestaurantOrder) : Flow<List<Restaurant>> {
    return repository.getStoredRestaurants().map { restaurants ->
        when(restaurantOrder) {
            is RestaurantOrder.Distance -> restaurants.sortedBy { it.distance }
            is RestaurantOrder.Rating -> restaurants.sortedBy { it.rating }
            is RestaurantOrder.Offers -> restaurants.sortedBy { it.hasOffer }
        }
        }
    }
}