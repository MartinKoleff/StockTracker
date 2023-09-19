package com.koleff.resumeproject.dependecyInjection

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.koleff.resumeproject.common.Constants
import com.koleff.resumeproject.data.remote.dto.StockMarketApi
import com.koleff.resumeproject.data.repositories.StockMarketRepositoryImpl
import com.koleff.resumeproject.domain.apiServices.repositories.interfaces.StockMarketRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideStockMarketApi(): StockMarketApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(StockMarketApi::class.java)
    }

    @Provides
    @Singleton
    fun provideStockMarketRepository(api: StockMarketApi): StockMarketRepository {
        return StockMarketRepositoryImpl(api)
    }
}