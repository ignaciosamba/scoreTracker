package com.ignacio.scoretracker.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ignacio.scoretracker.splash.network.InitLeagueRemoteDataSource
import com.ignacio.scoretracker.splash.network.InitLeaguesServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkCall {
    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://www.thesportsdb.com/")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideLeagueService(retrofit: Retrofit): InitLeaguesServices = retrofit.create(InitLeaguesServices::class.java)

    @Singleton
    @Provides
    fun provideCharacterRemoteDataSource(initLeague: InitLeaguesServices) = InitLeagueRemoteDataSource(initLeague)

}