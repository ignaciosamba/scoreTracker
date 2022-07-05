package com.ignacio.scoretracker.splash.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "leagues")
data class League (
    @PrimaryKey
    val idLeague : String,
    val strLeague :  String?,
    val strSport : String?,
    val strLeagueAlternate : String?)