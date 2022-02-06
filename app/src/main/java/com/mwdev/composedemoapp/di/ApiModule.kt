package com.mwdev.composedemoapp.di

import com.mwdev.composedemoapp.BuildConfig
import com.mwdev.composedemoapp.network.person.IMockApiService
import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun providesCoroutinesResponseCallAdapterFactory(): CoroutinesResponseCallAdapterFactory =
        CoroutinesResponseCallAdapterFactory.create()


    @Singleton
    @Provides
    fun provideMockApiService(
        okHttpClient: OkHttpClient,
        coroutinesResponseCallAdapterFactory: CoroutinesResponseCallAdapterFactory
    ): IMockApiService = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.MOCK_BASE_API_URL)
        .addCallAdapterFactory(coroutinesResponseCallAdapterFactory)
        .client(okHttpClient)
        .build()
        .create(IMockApiService::class.java)

}