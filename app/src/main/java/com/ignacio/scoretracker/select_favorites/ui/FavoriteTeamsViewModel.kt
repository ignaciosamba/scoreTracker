package com.ignacio.scoretracker.select_favorites.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ignacio.scoretracker.select_favorites.entities.FavoriteLeagues
import com.ignacio.scoretracker.select_favorites.entities.TeamDetails
import com.ignacio.scoretracker.select_favorites.repository.FavoriteLeaguesRepository
import com.ignacio.scoretracker.select_favorites.repository.TeamsFavoriteRepository
import com.ignacio.scoretracker.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteTeamsViewModel @Inject constructor(
    val repository: TeamsFavoriteRepository
) : ViewModel(){


    private var _favoriteLeagues = MutableLiveData<List<FavoriteLeagues>>()

    val favoriteLeagues : LiveData<List<FavoriteLeagues>>
        get() = _favoriteLeagues

    val teamsForFavoriteleagues : LiveData<Resource<List<TeamDetails>>>?
        get() = getAllteams()

    init {
        getAllFavoritesLeagues()
    }

    private fun getAllFavoritesLeagues() {
        CoroutineScope(Dispatchers.IO).launch{
            coroutineScope {
                _favoriteLeagues.postValue(repository.getAllFavoritesLeagues())
            }
        }
    }


    private fun getAllteams() : LiveData<Resource<List<TeamDetails>>>? {
        if(!_favoriteLeagues.value.isNullOrEmpty()) {
            return repository.getTeamsFromFavoritesLeague(_favoriteLeagues.value!!)
        } else {
            return null
        }
    }


}