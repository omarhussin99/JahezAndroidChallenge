package net.jahez.jahezchallenge.feature_restaurant.domain.model

sealed class RestaurantOrder {
    object Distance : RestaurantOrder()
    object Rating : RestaurantOrder()
    object Offers : RestaurantOrder()
}