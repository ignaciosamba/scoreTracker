package com.ignacio.scoretracker.splash.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "leaguesDetails")
data class LeagueDetails (
    @PrimaryKey
    val idLeague : String,
    val idSoccerXML : String?,
    val idAPIfootball : String?,
    val strSport : String?,
    val strLeague : String?,
    val strLeagueAlternate : String?,
    val intDivision : String?,
    val idCup : String?,
    val strCurrentSeason : String?,
    val intFormedYear : String?,
    val dateFirstEvent : String?,
    val strGender : String?,
    val strCountry : String?,
    val strWebsite : String?,
    val strLogo : String?,
    val strBadge : String?,
    var isSelectedAsFavorite : Boolean = false
)