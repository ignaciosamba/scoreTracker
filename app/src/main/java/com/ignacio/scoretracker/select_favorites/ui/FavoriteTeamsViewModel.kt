package com.ignacio.scoretracker.select_favorites.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ignacio.scoretracker.select_favorites.entities.FavoriteLeagues
import com.ignacio.scoretracker.select_favorites.entities.FavoriteTeams
import com.ignacio.scoretracker.select_favorites.entities.TeamDetails
import com.ignacio.scoretracker.select_favorites.model.ExpandableFavoriteTeamsModel
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

    val allTeamsForFavoriteleagues : LiveData<Resource<List<TeamDetails>>>?
        get() = getAllteams()

    private var _allTeamsForFavoriteleagues = MutableLiveData<List<ExpandableFavoriteTeamsModel>>()

    val teamsExpandableFavoriteByLeagues : LiveData<List<ExpandableFavoriteTeamsModel>?>
        get() = _allTeamsForFavoriteleagues

    fun init() {
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


    fun getAllteamsByLeague(teams : List<TeamDetails>) /*: LiveData<List<ExpandableFavoriteTeamsModel>?>*/ {
        var teamExpandableFavoriteTeamsModel = ArrayList<ExpandableFavoriteTeamsModel>()
        Log.d("SAMBA", "getAllteamsByLeague 1 _favoriteLeagues: ${_favoriteLeagues.value?.size}")
        if(!_favoriteLeagues.value.isNullOrEmpty()) {
            Log.d("SAMBA", "getAllteamsByLeague 2 teams: ${teams.size}")
            teams.let {
                _favoriteLeagues.value!!.forEach { favoriteLeagues ->
                        teamExpandableFavoriteTeamsModel.add(
                            ExpandableFavoriteTeamsModel(favoriteLeagues.nameLeague.toString(),
                                it.filter { it.idLeague == favoriteLeagues.idLeague }))
                }
            }
        }
        _allTeamsForFavoriteleagues.value = teamExpandableFavoriteTeamsModel
    }

    fun insertNewTeamFavorite(teamFavorite: TeamDetails, setAsFavorite : Boolean) {
        val favoriteTeam = FavoriteTeams(teamFavorite.idTeam,
            teamFavorite.idSoccerXML, teamFavorite.idAPIfootball,
            teamFavorite.intLoved, teamFavorite.strTeam, teamFavorite.strTeamShort,
            teamFavorite.strAlternate, teamFavorite.intFormedYear, teamFavorite.strSport,
            teamFavorite.strLeague, teamFavorite.idLeague, teamFavorite.strStadium,
            teamFavorite.strStadiumLocation, teamFavorite.strCountry,
            teamFavorite.strTeamBadge)

        CoroutineScope(Dispatchers.IO).launch {
            coroutineScope {
                if (setAsFavorite) {
                    repository.insertNewTeamFavorite(favoriteTeam)
                } else {
                    repository.deleteFavoriteTeam(favoriteTeam)
                }
            }
        }

    }

}