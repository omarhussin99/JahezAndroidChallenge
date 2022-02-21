package net.jahez.jahezchallenge.feature_restaurant.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import net.jahez.jahezchallenge.R
import net.jahez.jahezchallenge.databinding.RestaurantItemBinding
import net.jahez.jahezchallenge.feature_restaurant.domain.model.Restaurant
import javax.inject.Inject

class RestaurantAdapter @Inject constructor() :
    ListAdapter<Restaurant, RestaurantAdapter.RestaurantViewHolder>(RestaurantComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val binding =
            RestaurantItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RestaurantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class RestaurantViewHolder(private val binding: RestaurantItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(restaurant: Restaurant) {
            binding.apply {
                Glide.with(itemView)
                    .load(restaurant.image)
                    .into(productImage)

                productNameText.text = restaurant.name
                productDescriptionText.text = restaurant.description
                ratingText.text = restaurant.rating.toString()
                productHoursText.text = restaurant.hours
                if (restaurant.hasOffer == 1.0){
                    hasOfferText.setTextColor(hasOfferText.resources.getColor(R.color.green))
                    hasOfferText.text = hasOfferText.context.getString(R.string.has_offer)
                }else{
                    hasOfferText.setTextColor(hasOfferText.resources.getColor(R.color.red))
                    hasOfferText.text = hasOfferText.context.getString(R.string.no_offer)
                }
            }
        }
    }

    class RestaurantComparator : DiffUtil.ItemCallback<Restaurant>() {
        override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant) =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant) =
            oldItem == newItem
    }
}