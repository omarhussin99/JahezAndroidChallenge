package net.jahez.jahezchallenge.feature_restaurant.domain.use_case

import net.jahez.jahezchallenge.feature_restaurant.data.repository.FakeRestaurantRepository
import net.jahez.jahezchallenge.feature_restaurant.domain.model.Restaurant
import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import net.jahez.jahezchallenge.feature_restaurant.domain.model.RestaurantOrder


class GetSortedRestaurantsUseCaseTest {
    private lateinit var getSortedRestaurantsUseCase: GetSortedRestaurantsUseCase
    private lateinit var fakeRestaurantRepository: FakeRestaurantRepository


    @Before
    fun setup(){
        fakeRestaurantRepository = FakeRestaurantRepository()
        getSortedRestaurantsUseCase = GetSortedRestaurantsUseCase(fakeRestaurantRepository)

        val restaurantsToInsert = mutableListOf<Restaurant>()
        (1..100).forEachIndexed { _, i ->
            restaurantsToInsert.add(Restaurant(distance = i.toDouble(), rating = 100-i.toDouble()))
        }
        restaurantsToInsert.shuffle()
        fakeRestaurantRepository.insertRestaurants(restaurantsToInsert)
    }

    @Test
    fun order_restaurants_by_distance() = runBlocking {
       val restaurants= getSortedRestaurantsUseCase(RestaurantOrder.Distance).first()

        for(i in 0..restaurants.size - 2) {
            assertThat(restaurants[i].distance).isLessThan(restaurants[i+1].distance)
        }
    }

    @Test
    fun order_restaurants_by_rate() = runBlocking {
        val restaurants= getSortedRestaurantsUseCase(RestaurantOrder.Rating).first()

        for(i in 0..restaurants.size - 2) {
            assertThat(restaurants[i].rating).isLessThan(restaurants[i+1].rating)
        }
    }

}