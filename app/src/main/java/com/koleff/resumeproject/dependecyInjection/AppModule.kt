package com.koleff.resumeproject.dependecyInjection

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.koleff.resumeproject.BuildConfig
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
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //Local dependencies
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                val original = chain.request()

                val newUrl = original.url.newBuilder()
                    .scheme(Constants.SCHEME_LOCAL)
                    .host(Constants.BASE_URL)
                    .addQueryParameter("access_key", Constants.API_KEY)
                    .build()

                val request = original.newBuilder()
                    .method(original.method, original.body)
                    .url(newUrl)
                    .build()

                chain.proceed(request)
            })

        //Logging
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        if (BuildConfig.DEBUG) okHttpClientBuilder.addInterceptor(logging)

        return okHttpClientBuilder.build()
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
    fun provideStockMarketApi(okHttpClient: OkHttpClient, moshi: Moshi): StockMarketApi {
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