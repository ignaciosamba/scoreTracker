package com.ignacio.scoretracker.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ignacio.scoretracker.select_favorites.entities.TeamDetails
import com.ignacio.scoretracker.splash.entities.LeagueDetails

@Dao
interface TeamsDetailsDao {
    @Query("SELECT * FROM teamDetails WHERE idLeague =:leagueId")
    fun getTeamsByLeagueId(leagueId : String) : List<TeamDetails>

    @Query("SELECT * FROM teamDetails WHERE idTeam =:teamId")
    fun getLeagueById(teamId : String) : LiveData<TeamDetails>

    @Query("SELECT * FROM teamDetails")
    fun getAllTeamsDetails() : LiveData<List<TeamDetails>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTeamDetail(team: TeamDetails)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllTeamsDetails(teams: List<TeamDetails>)
}