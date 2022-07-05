package com.ignacio.scoretracker.select_favorites.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ignacio.scoretracker.databinding.FavoriteLeagueScreenFragmentBinding
import com.ignacio.scoretracker.select_favorites.entities.FavoriteLeagues
import com.ignacio.scoretracker.splash.entities.LeagueDetails
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesLeagueFragment : Fragment(), LeagueAdapter.FavoriteLeaguesItemListener{

    private lateinit var binding: FavoriteLeagueScreenFragmentBinding
    private val viewModel: FavoritesLeagueViewModel by viewModels()
    private val args: FavoritesLeagueFragmentArgs by navArgs()
    private lateinit var adapter: LeagueAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FavoriteLeagueScreenFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        val sportType = args.sportType
        viewModel.setSport(sportType)
        setupObservers()

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity!!.finish()
            }
        })

        binding.containedButton.setOnClickListener(continueListener)
    }

    private fun setupRecyclerView(){
        adapter = LeagueAdapter(this)
        binding.favoriteRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.favoriteRecycler.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.leagues.observe(viewLifecycleOwner, Observer {
            Log.d("SAMBA", "WTF")
            if (!it.isNullOrEmpty()) {
                adapter.setItems(it)
            }
        })
    }

    override fun onClickedLeague(league: LeagueDetails, mustSave : Boolean) {
        if (mustSave) {
            viewModel.insertNewFavoriteLeague(FavoriteLeagues(league.idLeague))
        } else {
            viewModel.deleteNewFavoriteLeague(FavoriteLeagues(league.idLeague))
        }
    }

    val continueListener = View.OnClickListener {
        //Start the new fragment with all the teams.
    }

}