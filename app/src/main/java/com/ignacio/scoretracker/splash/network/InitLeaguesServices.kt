package com.ignacio.scoretracker.splash.network

import com.ignacio.scoretracker.splash.entities.LeagueDetails
import com.ignacio.scoretracker.splash.entities.LeagueList
import com.ignacio.scoretracker.splash.entities.LeagueListDetails
import retrofit2.Response
import retrofit2.http.*

interface InitLeaguesServices {
    @POST("/api/v1/json/50130162/all_leagues.php")
    suspend fun getAllLeagues() : Response<LeagueList>

    @GET("/api/v1/json/50130162/lookupleague.php")
    suspend fun getLeagueDetails(@Query("id") id : String) : Response<LeagueListDetails>
}