package net.jahez.jahezchallenge.feature_home.data.dto


import com.google.gson.annotations.SerializedName

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
    val id: Int? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("rating")
    val rating: Double? = null
)