package com.ignacio.scoretracker.select_favorites.repository

import android.util.Log
import com.ignacio.scoretracker.database.dao.FavoriteLeaguesDao
import com.ignacio.scoretracker.database.dao.LeagueDetailsDao
import com.ignacio.scoretracker.select_favorites.entities.FavoriteLeagues
import com.ignacio.scoretracker.splash.entities.LeagueDetails
import com.ignacio.scoretracker.network.performGetLeaguesDB
import javax.inject.Inject

class FavoriteLeaguesRepository @Inject constructor(
    val leagueDetailsDao: LeagueDetailsDao,
    val favoriteLeaguesDao : FavoriteLeaguesDao
) {

    fun getAllLeagues() = performGetLeaguesDB(databaseQuery = { leagueDetailsDao.getAllLeaguesDetails()})

    fun getLeaguesBySport(sport : String) : List<LeagueDetails> {
        Log.d("SAMBA", "getLeaguesBySport with sport: $sport")
        val list  = ArrayList<LeagueDetails>()
        if(sport.equals("Others", ignoreCase = true)) {
            list.addAll(leagueDetailsDao.getLeagueByNotSport("Soccer", "Basketball"))
        } else {
            list.addAll(leagueDetailsDao.getLeagueBySport(sport))
        }
        Log.d("SAMBA", "RETURN LIST WITH: ${list.size}")
        return list
    }

    fun getAllFavoriteLeague() : List<FavoriteLeagues>{
        return favoriteLeaguesDao.getFavoriteLeagues()
    }

    fun insertNewFavoriteLeague(league : FavoriteLeagues) {
        favoriteLeaguesDao.insertFavoriteLeague(league)
    }

    fun deleteNewFavoriteLeague(league : FavoriteLeagues) {
        favoriteLeaguesDao.deleteFavoriteLeague(league.idLeague)
    }
}