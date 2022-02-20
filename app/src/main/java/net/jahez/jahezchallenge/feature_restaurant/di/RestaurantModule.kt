package net.jahez.jahezchallenge.feature_restaurant.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.jahez.jahezchallenge.feature_restaurant.data.local.RestaurantsDatabase
import net.jahez.jahezchallenge.feature_restaurant.data.remote.ApiServices
import net.jahez.jahezchallenge.feature_restaurant.data.repository.RestaurantRepositoryImpl
import net.jahez.jahezchallenge.feature_restaurant.domain.repository.RestaurantRepository
import net.jahez.jahezchallenge.feature_restaurant.domain.use_case.GetRestaurantsUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RestaurantModule {

    @Provides
    @Singleton
    fun provideWordInfoRepository(
        db: RestaurantsDatabase,
        api: ApiServices
    ): RestaurantRepository {
        return RestaurantRepositoryImpl(api, db.dao)
    }

    @Provides
    @Singleton
    fun provideWordInfoDatabase(app: Application): RestaurantsDatabase {
        return Room.databaseBuilder(
            app, RestaurantsDatabase::class.java, "restaurants_db"
        ).build()
    }

}