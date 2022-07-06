package com.ignacio.scoretracker.select_favorites.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ignacio.scoretracker.select_favorites.entities.FavoriteLeagues
import com.ignacio.scoretracker.select_favorites.repository.FavoriteLeaguesRepository
import com.ignacio.scoretracker.splash.entities.LeagueDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class FavoritesLeagueViewModel @Inject constructor(
    val repository: FavoriteLeaguesRepository
) : ViewModel(){

    private var _leagues = MutableLiveData<List<LeagueDetails>>()

    private var listOfFavoritesIds = ArrayList<String>()

    val leagues: LiveData<List<LeagueDetails>>
        get() = _leagues

    init {
        getAllFavoritesLeagues()
    }

    fun setSport(sport : String) {
        CoroutineScope(Dispatchers.IO).launch{
            coroutineScope {
                Log.d("SAMBA", "Coroutine")
                try {
                    val listDetailLeagues = repository.getLeaguesBySport(sport)
                    listDetailLeagues.forEach{
                        if (listOfFavoritesIds.contains(it.idLeague)) {
                            it.isSelectedAsFavorite = true
                        }
                    }
                    _leagues.postValue(listDetailLeagues)
                } catch (e : Exception) {
                    Log.d("SAMBA", "ERROR: ${e.message}")
                }
            }
        }
    }

    fun getAllFavoritesLeagues() {
        CoroutineScope(Dispatchers.IO).launch {
            coroutineScope {
                repository.getAllFavoriteLeague().forEach {
                    listOfFavoritesIds.add(it.idLeague)
                }
            }
        }
    }

    fun insertNewFavoriteLeague(league : FavoriteLeagues) {
        CoroutineScope(Dispatchers.IO).launch{
            coroutineScope {
                repository.insertNewFavoriteLeague(league)
            }
        }
    }

    fun deleteNewFavoriteLeague(league : FavoriteLeagues) {
        CoroutineScope(Dispatchers.IO).launch{
            coroutineScope {
                repository.deleteNewFavoriteLeague(league)
            }
        }
    }
}