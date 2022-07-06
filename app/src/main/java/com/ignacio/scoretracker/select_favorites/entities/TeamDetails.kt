package com.ignacio.scoretracker.select_favorites.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teamDetails")
class TeamDetails (
    @PrimaryKey
    val idTeam : String,
    val idSoccerXML : String?,
    val idAPIfootball : String?,
    val intLoved : String?,
    val strTeam : String?,
    val strTeamShort : String?,
    val strAlternate : String?,
    val intFormedYear : String?,
    val strSport : String?,
    val strLeague : String?,
    val idLeague : String?,
    val strStadium : String?,
    val strStadiumLocation : String?,
    val strCountry : String?,
    val strTeamBadge : String?)