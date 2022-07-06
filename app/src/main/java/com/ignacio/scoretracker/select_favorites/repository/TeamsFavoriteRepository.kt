package com.ignacio.scoretracker.select_favorites.repository

import com.ignacio.scoretracker.database.dao.FavoriteLeaguesDao
import com.ignacio.scoretracker.database.dao.FavoriteTeamsDao
import com.ignacio.scoretracker.database.dao.TeamsDetailsDao
import com.ignacio.scoretracker.network.performGetInitListLeaguesOperation
import com.ignacio.scoretracker.select_favorites.entities.FavoriteLeagues
import com.ignacio.scoretracker.select_favorites.entities.FavoriteTeams
import com.ignacio.scoretracker.select_favorites.network.FavoritesRemoteDataSource
import javax.inject.Inject

class TeamsFavoriteRepository @Inject constructor(
    private val teamsDetailsDao : TeamsDetailsDao,
    private val leaguesFavoritesDao: FavoriteLeaguesDao,
    private val favoriteTeamsDao: FavoriteTeamsDao,
    private val favoritesRemoteDataSource: FavoritesRemoteDataSource
) {

    fun getAllFavoritesLeagues () : List<FavoriteLeagues>{
        return leaguesFavoritesDao.getFavoriteLeagues()
    }

    fun getTeamsFromFavoritesLeague(favoriteLeagues : List<FavoriteLeagues>) =
        performGetInitListLeaguesOperation(
        databaseQuery = { teamsDetailsDao.getAllTeamsDetails() },
        networkCall = { favoritesRemoteDataSource.getTeamsDetails(favoriteLeagues) },
        saveCallResult = { teamsDetailsDao.insertAllTeamsDetails(it.teams) })

    fun insertNewTeamFavorite(team : FavoriteTeams) {
        favoriteTeamsDao.insertFavoriteTeam(team)
    }

    fun deleteFavoriteTeam(team : FavoriteTeams) {
        favoriteTeamsDao.deleteFavoriteTeam(team.idTeam)
    }
}