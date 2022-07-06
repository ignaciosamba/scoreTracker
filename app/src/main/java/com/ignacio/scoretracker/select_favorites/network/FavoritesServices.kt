package com.ignacio.scoretracker.select_favorites.network

import com.ignacio.scoretracker.select_favorites.entities.TeamsInLeague
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FavoritesServices {

    @GET("/api/v1/json/50130162/lookup_all_teams.php")
    suspend fun getTeamsDetailsbyLeagueId(@Query("id") id : String) : Response<TeamsInLeague>

}