package com.ignacio.scoretracker.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ignacio.scoretracker.select_favorites.entities.FavoriteLeagues
import com.ignacio.scoretracker.select_favorites.entities.FavoriteTeams

@Dao
interface FavoriteTeamsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteTeam(league: FavoriteTeams)

    @Query("DELETE FROM favoriteTeams WHERE idLeague = :leagueId")
    fun deleteFavoriteTeam(leagueId : String)

    @Query("SELECT * FROM favoriteTeams")
    fun getFavoriteLeagues() : List<FavoriteTeams>
}