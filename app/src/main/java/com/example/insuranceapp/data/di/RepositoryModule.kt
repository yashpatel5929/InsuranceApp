package com.example.insuranceapp.data.di

import android.content.Context
import com.example.insuranceapp.data.constants.Constants
import com.example.insuranceapp.data.impls.LoginAuthRepositoryImpl
import com.example.insuranceapp.data.remote.api.LoginAPi
import com.example.insuranceapp.data.storage.pref.AppPreferences
import com.example.insuranceapp.domain.repository.LoginAuthRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideLoginAuthRepository(repository: LoginAuthRepositoryImpl): LoginAuthRepository {
        return repository
    }
}