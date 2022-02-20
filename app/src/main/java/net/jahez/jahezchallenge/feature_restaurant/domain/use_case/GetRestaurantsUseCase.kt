package net.jahez.jahezchallenge.feature_restaurant.domain.use_case

import kotlinx.coroutines.flow.Flow
import net.jahez.jahezchallenge.core.util.Resource
import net.jahez.jahezchallenge.feature_restaurant.domain.model.Restaurant
import net.jahez.jahezchallenge.feature_restaurant.domain.repository.RestaurantRepository
import javax.inject.Inject

class GetRestaurantsUseCase @Inject constructor(
    private val repository: RestaurantRepository
    ) {
    operator fun invoke() : Flow<Resource<List<Restaurant>>> = repository.getRestaurants()
}