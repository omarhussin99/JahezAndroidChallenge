package net.jahez.jahezchallenge.feature_restaurant.data.remote.dto


import com.google.gson.annotations.SerializedName
import net.jahez.jahezchallenge.feature_restaurant.data.local.entity.RestaurantEntity

data class RestaurantDto(
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("distance")
    val distance: Double? = null,
    @SerializedName("hasOffer")
    val hasOffer: Boolean? = null,
    @SerializedName("hours")
    val hours: String? = null,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("rating")
    val rating: Double? = null
){
    fun toRestaurantEntity() : RestaurantEntity =
        RestaurantEntity(
            description = description,
            distance = distance,
            hasOffer = hasOffer,
            hours = hours,
            id = id,
            image = image,
            name = name,
            rating = rating
        )

}