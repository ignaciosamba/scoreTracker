package com.ignacio.scoretracker.di

import com.ignacio.scoretracker.database.dao.LeagueDao
import com.ignacio.scoretracker.database.dao.LeagueDetailsDao
import com.ignacio.scoretracker.select_favorites.repository.FavoriteLeaguesRepository
import com.ignacio.scoretracker.select_favorites.repository.TeamsFavoriteRepository
import com.ignacio.scoretracker.select_favorites.ui.FavoriteTeamsViewModel
import com.ignacio.scoretracker.select_favorites.ui.FavoritesLeagueViewModel
import com.ignacio.scoretracker.splash.network.InitLeagueRemoteDataSource
import com.ignacio.scoretracker.splash.repository.InitLeaguesRepository
import com.ignacio.scoretracker.splash.ui.SplashScreenViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModels {

    @Provides
    @ViewModelScoped
    fun providesSplashViewModel(repository: InitLeaguesRepository): SplashScreenViewModel = SplashScreenViewModel(repository)

    @Provides
    @ViewModelScoped
    fun providesFavoriteLeaguesViewModel(repository: FavoriteLeaguesRepository):
            FavoritesLeagueViewModel = FavoritesLeagueViewModel(repository)

    @Provides
    @ViewModelScoped
    fun providesFavoriteTeamsViewModel(repository: TeamsFavoriteRepository):
            FavoriteTeamsViewModel = FavoriteTeamsViewModel(repository)
}