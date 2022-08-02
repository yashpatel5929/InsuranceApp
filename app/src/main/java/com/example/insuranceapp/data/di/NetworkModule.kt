package com.example.insuranceapp.data.di

import android.content.Context
import com.example.insuranceapp.data.constants.Constants
import com.example.insuranceapp.data.remote.api.LoginAPi
import com.example.insuranceapp.data.storage.pref.AppPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun providerAppPrefs(@ApplicationContext context: Context): AppPreferences {
        return AppPreferences(context)
    }


    val loggInInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    val clientBuilder = OkHttpClient.Builder().addInterceptor(loggInInterceptor)
    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(clientBuilder.build())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideUserLoginApi(retrofit: Retrofit): LoginAPi = retrofit.create(LoginAPi::class.java)
}