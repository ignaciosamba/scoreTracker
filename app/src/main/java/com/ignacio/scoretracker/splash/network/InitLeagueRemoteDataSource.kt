package com.ignacio.scoretracker.splash.network

import android.util.Log
import com.ignacio.scoretracker.network.BaseDataSource
import com.ignacio.scoretracker.utils.LeaguesIDs
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class InitLeagueRemoteDataSource @Inject constructor(
    private val initLeaguesServices: InitLeaguesServices
) : BaseDataSource() {

    suspend fun getInitLeagues() = getResult { initLeaguesServices.getAllLeagues() }

    suspend fun getLeagueDetails() =
        coroutineScope {
            Log.d("SAMBA", "getLeaguesDetails")
            enumValues<LeaguesIDs>().map {
                async {
                    getResult { initLeaguesServices.getLeagueDetails(it.id)}
                }
            }.awaitAll()
        }
}