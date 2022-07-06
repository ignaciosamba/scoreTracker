package com.ignacio.scoretracker.select_favorites.network

import android.util.Log
import com.ignacio.scoretracker.database.dao.FavoriteLeaguesDao
import com.ignacio.scoretracker.network.BaseDataSource
import com.ignacio.scoretracker.select_favorites.entities.FavoriteLeagues
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class FavoritesRemoteDataSource @Inject constructor(
    private val favoritesServices: FavoritesServices
) : BaseDataSource(){

    suspend fun getTeamsDetails(favoritesLeagues : List<FavoriteLeagues>) =
        coroutineScope {
            Log.d("SAMBA", "getLeaguesDetails")
            favoritesLeagues.map {
                async {
                    getResult { favoritesServices.getTeamsDetailsbyLeagueId(it.idLeague)}
                }
            }.awaitAll()
        }
}