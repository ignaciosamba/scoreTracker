package com.ignacio.scoretracker.splash.ui

import android.util.Log
import androidx.lifecycle.*
import com.ignacio.scoretracker.splash.entities.League
import com.ignacio.scoretracker.splash.entities.LeagueDetails
import com.ignacio.scoretracker.splash.repository.InitLeaguesRepository
import com.ignacio.scoretracker.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val repository: InitLeaguesRepository
): ViewModel() {

    private var lastList = MutableLiveData<Resource<List<LeagueDetails>>>()


    /**
     * Public view of All-Leagues live data.
     */
    val league: LiveData<Resource<List<League>>>
        get() = repository.getAllLeagues()

    /**
     * Public view of All-Leagues live data.
     */
    val leaguesDetails:  LiveData<Resource<List<LeagueDetails>>>
        get() = callLeagueDetails()

    private fun callLeagueDetails(): LiveData<Resource<List<LeagueDetails>>> {
        Log.d("SAMBA", "calling callLeagueDetails")
        val list = repository.getLeagueDetails()
        Log.d("SAMBA", "finishing callLeagueDetails")
        return list
    }

}