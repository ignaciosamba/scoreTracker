package com.ignacio.scoretracker.di

import android.content.Context
import com.ignacio.scoretracker.database.LeaguesDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBases {

    @Singleton
    @Provides
    fun provideLeagueDatabase(@ApplicationContext appContext: Context) = LeaguesDataBase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideLeaguesDao(db: LeaguesDataBase) = db.leaguesDao()

    @Singleton
    @Provides
    fun provideLeaguesDetailsDao(db: LeaguesDataBase) = db.leaguesDetailsDao()

    @Singleton
    @Provides
    fun provideFavoriteLeaguesDao(db: LeaguesDataBase) = db.favoriteLeaguesDao()

    @Singleton
    @Provides
    fun provideTeamsDetailsDao(db: LeaguesDataBase) = db.teamsDetailsDao()
}