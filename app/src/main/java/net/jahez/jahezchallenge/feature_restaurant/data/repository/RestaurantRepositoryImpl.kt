package net.jahez.jahezchallenge.feature_restaurant.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.jahez.jahezchallenge.core.util.Resource
import net.jahez.jahezchallenge.feature_restaurant.data.local.RestaurantDao
import net.jahez.jahezchallenge.feature_restaurant.data.remote.ApiServices
import net.jahez.jahezchallenge.feature_restaurant.domain.model.Restaurant
import net.jahez.jahezchallenge.feature_restaurant.domain.repository.RestaurantRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RestaurantRepositoryImpl @Inject constructor (
    private val apiServices: ApiServices,
    private val dao: RestaurantDao
    ): RestaurantRepository {

    override fun getRestaurants(): Flow<Resource<List<Restaurant>>> = flow {
        emit(Resource.Loading())

        val localRestaurants = dao.getAllRestaurants()
            .map { it.toRestaurant() }
            .sortedBy {it.distance}

        try {
            val remoteRestaurants = apiServices.getRestaurants()
            dao.deleteAllRestaurants()
            dao.insertRestaurants(remoteRestaurants.map { it.toRestaurantEntity() })
        }catch(e: HttpException) {
            emit(Resource.Error(
                message = "Oops, something went wrong!",
                data = localRestaurants
            ))
        }catch(e: IOException) {
            emit(Resource.Error(
                message = "Couldn't reach server, check your internet connection.",
                data = localRestaurants
            ))
        }

        val newLocalRestaurants = dao.getAllRestaurants().
        map { it.toRestaurant() }.
        sortedBy {it.distance}
        emit(Resource.Success(data = newLocalRestaurants))
    }

    override fun getStoredRestaurants(): Flow<List<Restaurant>> = flow {
        emit(dao.getAllRestaurants().map { it.toRestaurant() })
    }

}