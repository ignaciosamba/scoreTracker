package com.ignacio.scoretracker.select_favorites.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ignacio.scoretracker.splash.entities.LeagueDetails

@Entity(tableName = "favoriteLeagues")
data class FavoriteLeagues (
    @PrimaryKey
    val idLeague : String,
    val nameLeague : String?
)