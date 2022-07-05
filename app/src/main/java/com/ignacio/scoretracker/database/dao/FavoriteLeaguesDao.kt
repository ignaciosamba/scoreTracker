package com.ignacio.scoretracker.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ignacio.scoretracker.select_favorites.entities.FavoriteLeagues
import com.ignacio.scoretracker.splash.entities.League

@Dao
interface FavoriteLeaguesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteLeague(league: FavoriteLeagues)

    @Query("DELETE FROM favoriteLeagues WHERE idLeague = :leagueId")
    fun deleteFavoriteLeague(leagueId : String)

    @Query("SELECT * FROM favoriteLeagues")
    fun getFavoriteLeagues() : List<String>
}