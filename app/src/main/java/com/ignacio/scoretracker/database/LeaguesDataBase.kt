package com.ignacio.scoretracker.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ignacio.scoretracker.database.dao.*
import com.ignacio.scoretracker.select_favorites.entities.FavoriteLeagues
import com.ignacio.scoretracker.select_favorites.entities.FavoriteTeams
import com.ignacio.scoretracker.select_favorites.entities.TeamDetails
import com.ignacio.scoretracker.splash.entities.League
import com.ignacio.scoretracker.splash.entities.LeagueDetails

@Database(entities = [League::class, LeagueDetails::class,
    FavoriteLeagues::class, TeamDetails::class, FavoriteTeams::class],
    version = 9, exportSchema = false)
abstract class LeaguesDataBase : RoomDatabase() {

    abstract fun leaguesDao(): LeagueDao
    abstract fun leaguesDetailsDao(): LeagueDetailsDao
    abstract fun favoriteLeaguesDao(): FavoriteLeaguesDao
    abstract fun teamsDetailsDao(): TeamsDetailsDao
    abstract fun favoriteTeamsDao(): FavoriteTeamsDao

    companion object {
        @Volatile private var instance: LeaguesDataBase? = null

        fun getDatabase(context: Context): LeaguesDataBase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, LeaguesDataBase::class.java, "SportTracker")
                .fallbackToDestructiveMigration()
                .build()
    }
}