package com.koleff.resumeproject.dependecyInjection

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.koleff.resumeproject.common.Constants
import com.koleff.resumeproject.data.remote.StockMarketApi
import com.koleff.resumeproject.data.repositories.StockMarketRepositoryImpl
import com.koleff.resumeproject.domain.apiServices.StockMarketApiService
import com.koleff.resumeproject.domain.repositories.StockMarketRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.Arrays
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //OkHttpClient configurations
    private fun apiKeyAsHeader(it: Interceptor.Chain) = it.proceed(
        it.request()
            .newBuilder()
            .addHeader("access_key", Constants.API_KEY)
            .build()
    )

    private fun configureUrl(it: Interceptor.Chain) = it.proceed(
        it.request()
            .newBuilder()
            .url(Constants.SCHEME_LOCAL + Constants.BASE_URL + "//")
            .build()
    )

    //Local dependencies
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { apiKeyAsHeader(it) }
            .addInterceptor { configureUrl(it) }
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    //Global dependencies
    @Provides
    @Singleton
    fun provideStockMarketApi(): StockMarketApi {
        val okHttpClient: OkHttpClient = provideOkHttpClient() //inject somehow?

        val moshi: Moshi = provideMoshi() //inject somehow?

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_LOCAL_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(StockMarketApi::class.java)
    }

    @Provides
    @Singleton
    fun provideStockMarketRepository(api: StockMarketApi): StockMarketRepository {
        return StockMarketRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providesStockMarketApiService(stockMarketRepository: StockMarketRepository): StockMarketApiService {
        return StockMarketApiService(stockMarketRepository)
    }
}