package com.ignacio.scoretracker.di

import com.ignacio.scoretracker.database.dao.FavoriteLeaguesDao
import com.ignacio.scoretracker.database.dao.LeagueDao
import com.ignacio.scoretracker.database.dao.LeagueDetailsDao
import com.ignacio.scoretracker.select_favorites.repository.FavoriteLeaguesRepository
import com.ignacio.scoretracker.splash.network.InitLeagueRemoteDataSource
import com.ignacio.scoretracker.splash.repository.InitLeaguesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Repositories {

    @Singleton
    @Provides
    fun provideInitLeaguesRepository(remoteDataSource: InitLeagueRemoteDataSource,
                                     leagueLocalSource: LeagueDao,
                                     leagueDetailsLocalSource: LeagueDetailsDao
    ) = InitLeaguesRepository(remoteDataSource, leagueLocalSource, leagueDetailsLocalSource)

    @Singleton
    @Provides
    fun provideFavoriteLeaguesRepository(leagueDetailsLocalSource: LeagueDetailsDao,
                                         favoriteLeaguesLocalSource : FavoriteLeaguesDao
    ) = FavoriteLeaguesRepository(leagueDetailsLocalSource, favoriteLeaguesLocalSource)
}