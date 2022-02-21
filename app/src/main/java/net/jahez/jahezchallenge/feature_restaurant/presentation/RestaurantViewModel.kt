package net.jahez.jahezchallenge.feature_restaurant.presentation

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import net.jahez.jahezchallenge.feature_restaurant.domain.model.Restaurant
import net.jahez.jahezchallenge.feature_restaurant.domain.model.RestaurantOrder
import net.jahez.jahezchallenge.feature_restaurant.domain.use_case.GetRestaurantsUseCase
import net.jahez.jahezchallenge.feature_restaurant.domain.use_case.GetSortedRestaurantsUseCase
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    getRestaurantsUseCase: GetRestaurantsUseCase,
    private val getSortedRestaurantsUseCase: GetSortedRestaurantsUseCase
) : ViewModel() {
    val restaurantsLiveData = getRestaurantsUseCase.invoke().asLiveData()

    private val _sortedRestaurantLiveData = MutableLiveData<List<Restaurant>>()
    val sortedRestaurantLiveData: LiveData<List<Restaurant>>
        get() = _sortedRestaurantLiveData

    fun sortedRestaurantsLiveData(order: RestaurantOrder) {
        viewModelScope.launch {
            getSortedRestaurantsUseCase.invoke(order).collect {
                _sortedRestaurantLiveData.postValue(it)
            }
        }
    }
}