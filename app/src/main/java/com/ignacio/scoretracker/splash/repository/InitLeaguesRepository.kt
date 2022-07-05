package com.ignacio.scoretracker.splash.repository

import com.ignacio.scoretracker.database.dao.LeagueDao
import com.ignacio.scoretracker.database.dao.LeagueDetailsDao
import com.ignacio.scoretracker.splash.network.InitLeagueRemoteDataSource
import com.ignacio.scoretracker.splash.network.performGetInitLeaguesOperation
import com.ignacio.scoretracker.splash.network.performGetInitListLeaguesOperation
import javax.inject.Inject

class InitLeaguesRepository @Inject constructor(
    private val remoteDataSource: InitLeagueRemoteDataSource,
    private val localDataSourceLeague: LeagueDao,
    private val localDataSourceLeagueDetails: LeagueDetailsDao
) {
    fun getAllLeagues() = performGetInitLeaguesOperation(
        databaseQuery = { localDataSourceLeague.getAllLeagues() },
        networkCall = { remoteDataSource.getInitLeagues() },
        saveCallResult = { localDataSourceLeague.putAllLeagues(it.leagues)}
    )

    fun getLeagueDetails() = performGetInitListLeaguesOperation(
        databaseQuery = { localDataSourceLeagueDetails.getAllLeaguesDetails() },
        networkCall = { remoteDataSource.getLeagueDetails() },
        saveCallResult = { localDataSourceLeagueDetails.putAllLeagueDetail(it.leagues) }
    )
}