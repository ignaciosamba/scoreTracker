package com.ignacio.scoretracker.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ignacio.scoretracker.splash.entities.League
import com.ignacio.scoretracker.splash.entities.LeagueDetails
import com.ignacio.scoretracker.splash.entities.LeagueList

@Dao
interface LeagueDao {

    @Query("SELECT * FROM leagues")
    fun getAllLeagues() : LiveData<List<League>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun putAllLeagues(league: List<League>)

}