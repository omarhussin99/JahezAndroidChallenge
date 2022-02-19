package net.jahez.jahezchallenge.feature_home.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


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
)