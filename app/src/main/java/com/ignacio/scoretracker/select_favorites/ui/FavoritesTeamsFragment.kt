package com.ignacio.scoretracker.select_favorites.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ignacio.scoretracker.databinding.FavoriteTeamsScreenFragmentBinding
import com.ignacio.scoretracker.select_favorites.entities.FavoriteLeagues
import com.ignacio.scoretracker.select_favorites.entities.TeamDetails
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavoritesTeamsFragment: Fragment(), TeamAdapter.FavoriteTeamsItemListener {

    private lateinit var binding: FavoriteTeamsScreenFragmentBinding
    private val viewModel: FavoriteTeamsViewModel by viewModels()
    private lateinit var adapter: TeamAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FavoriteTeamsScreenFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.init()
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView(){
        adapter = TeamAdapter(this)
        binding.favoriteTeamsRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.favoriteTeamsRecycler.adapter = adapter
    }


    private fun setupObservers() {
        viewModel.favoriteLeagues.observe(viewLifecycleOwner) { leagues ->
            Log.d("SAMBA", "FAVORITES LEAGUES ARE: ${leagues.size}")
            viewModel.allTeamsForFavoriteleagues?.observe(viewLifecycleOwner, Observer { teamList ->
                Log.d("SAMBA", "TEAMS OBSERVER")
                if (!teamList.data.isNullOrEmpty()) {
                    viewModel.getAllteamsByLeague(teamList.data)
                    Log.d("SAMBA", "TEAMS OBSERVER WITH SIZE ${teamList.data.size}")
                }
            })
        }
        viewModel.teamsExpandableFavoriteByLeagues.observe(viewLifecycleOwner, Observer { teamsByLeague ->
            Log.d("SAMBA", "TEAMS BY LEAGUE OBSERVER WITH SIZE ${teamsByLeague?.size}")
            teamsByLeague.let { adapter.setItems(it!!) }
        })

    }

    override fun onClickedLeague(teamFavorite: TeamDetails, setAsFavorite : Boolean) {
        Log.d("SAMBA", "SELECTED TEAM: ${teamFavorite.strTeam}")
        viewModel.insertNewTeamFavorite(teamFavorite, setAsFavorite)
    }

}