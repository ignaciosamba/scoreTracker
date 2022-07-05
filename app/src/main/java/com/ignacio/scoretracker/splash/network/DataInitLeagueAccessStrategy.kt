package com.ignacio.scoretracker.splash.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.ignacio.scoretracker.splash.entities.League
import com.ignacio.scoretracker.splash.entities.LeagueDetails
import com.ignacio.scoretracker.splash.entities.LeagueList
import com.ignacio.scoretracker.utils.LeaguesFilters
import com.ignacio.scoretracker.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlin.math.log

fun <T, A> performGetInitLeaguesOperation (databaseQuery: () -> LiveData<T>,
                                    networkCall: suspend () -> Resource<A>,
                                    saveCallResult: suspend (A) -> Unit): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val source = databaseQuery.invoke().map { Resource.success(it) }
        emitSource(source)

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Resource.Status.SUCCESS) {
            if (responseStatus.data != null) {
                saveCallResult(responseStatus.data)
            }
        } else if (responseStatus.status == Resource.Status.ERROR) {
            emit(Resource.error(responseStatus.message!!))
            emitSource(source)
        }
}

fun <T, A> performGetInitListLeaguesOperation (databaseQuery: () -> LiveData<T>,
                                           networkCall: suspend () -> List<Resource<A>>,
                                           saveCallResult: suspend (A) -> Unit): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        Log.d("SAMBA", "ENTRANDO AL DISPATSCHER 1")
        emit(Resource.loading())
        Log.d("SAMBA", "ENTRANDO AL DISPATSCHER 2")
        val source = databaseQuery.invoke().map { Resource.success(it) }
        Log.d("SAMBA", "EMITING")
        emitSource(source)
        val last = this.latestValue


        Log.d("SAMBA", "before responseStatus")
        val responseStatus = networkCall.invoke()
        if(responseStatus != last) {
            Log.d("SAMBA", "responseStatus : ${responseStatus.size}")
            responseStatus.forEach {
                if (it.status == Resource.Status.SUCCESS) {
                    if (it.data != null) {
                        saveCallResult(it.data)
                    }
                } else if (it.status == Resource.Status.ERROR) {
                    emit(Resource.error(it.message!!))
                    emitSource(source)
                }
            }
        }
    }

fun <T> performGetLeaguesDB (databaseQuery: () -> LiveData<T>): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        Log.d("SAMBA", "RETRIEVING LEAGUES")
        emit(Resource.loading())
        Log.d("SAMBA", "RETRIEVING LEAGUES2")
        val source = databaseQuery.invoke().map { Resource.success(it) }
        Log.d("SAMBA", "RETRIEVING LEAGUES")
        emitSource(source)
    }

fun <T> performInsertLeaguesDB (databaseQuery: suspend () -> Unit): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        val source = databaseQuery.invoke()
    }
