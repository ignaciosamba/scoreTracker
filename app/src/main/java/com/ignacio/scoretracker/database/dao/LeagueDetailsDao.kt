package com.ignacio.scoretracker.database.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ignacio.scoretracker.splash.entities.LeagueDetails
import com.ignacio.scoretracker.splash.entities.LeagueListDetails

@Dao
interface LeagueDetailsDao {

    @Query("SELECT * FROM leaguesDetails WHERE strSport =:strSport")
    fun getLeagueBySport(strSport : String) : List<LeagueDetails>

    @Query("SELECT * FROM leaguesDetails WHERE strSport NOT IN (:strSport1, :strSport2)")
    fun getLeagueByNotSport(strSport1 : String, strSport2: String) : List<LeagueDetails>

    @Query("SELECT * FROM leaguesDetails WHERE idLeague =:leagueId")
    fun getLeagueById(leagueId : String) : LiveData<LeagueDetails>

    @Query("SELECT * FROM leaguesDetails")
    fun getAllLeaguesDetails() : LiveData<List<LeagueDetails>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun putLeagueDetail(league: LeagueDetails)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun putAllLeagueDetail(league: List<LeagueDetails>)
}