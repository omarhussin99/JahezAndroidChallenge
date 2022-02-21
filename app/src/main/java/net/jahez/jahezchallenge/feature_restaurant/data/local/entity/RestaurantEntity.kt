package net.jahez.jahezchallenge.feature_restaurant.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import net.jahez.jahezchallenge.feature_restaurant.domain.model.Restaurant


@Entity(tableName = "restaurants")
data class RestaurantEntity(
    @PrimaryKey
    val id: Int,
    val description: String? = null,
    val distance: Double? = null,
    val hasOffer: Boolean? = null,
    val hours: String? = null,
    val image: String? = null,
    val name: String? = null,
    val rating: Double? = null
) {
    fun toRestaurant(): Restaurant =
        Restaurant(
            description = description,
            distance = distance,
            hasOffer = if (hasOffer!!) 1.0 else 0.0,
            hours = hours,
            image = image,
            name = name,
            rating = rating
        )
}