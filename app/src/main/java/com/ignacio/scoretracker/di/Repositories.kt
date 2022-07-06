package com.ignacio.scoretracker.di

import com.ignacio.scoretracker.database.dao.*
import com.ignacio.scoretracker.select_favorites.network.FavoritesRemoteDataSource
import com.ignacio.scoretracker.select_favorites.repository.FavoriteLeaguesRepository
import com.ignacio.scoretracker.select_favorites.repository.TeamsFavoriteRepository
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

    @Singleton
    @Provides
    fun provideFavoriteTeamsRepository(teamsDetailsDao : TeamsDetailsDao,
                                       leaguesFavoritesDao: FavoriteLeaguesDao,
                                       favoriteTeamsDao: FavoriteTeamsDao,
                                       favoritesRemoteDataSource: FavoritesRemoteDataSource
    ) = TeamsFavoriteRepository(teamsDetailsDao, leaguesFavoritesDao,
        favoriteTeamsDao, favoritesRemoteDataSource)
}