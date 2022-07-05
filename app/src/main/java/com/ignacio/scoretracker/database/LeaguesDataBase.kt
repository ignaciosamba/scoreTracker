package com.ignacio.scoretracker.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ignacio.scoretracker.database.dao.FavoriteLeaguesDao
import com.ignacio.scoretracker.database.dao.LeagueDao
import com.ignacio.scoretracker.database.dao.LeagueDetailsDao
import com.ignacio.scoretracker.select_favorites.entities.FavoriteLeagues
import com.ignacio.scoretracker.splash.entities.League
import com.ignacio.scoretracker.splash.entities.LeagueDetails

@Database(entities = [League::class, LeagueDetails::class, FavoriteLeagues::class],
    version = 7, exportSchema = false)
abstract class LeaguesDataBase : RoomDatabase() {

    abstract fun leaguesDao(): LeagueDao
    abstract fun leaguesDetailsDao(): LeagueDetailsDao
    abstract fun favoriteLeaguesDao(): FavoriteLeaguesDao


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