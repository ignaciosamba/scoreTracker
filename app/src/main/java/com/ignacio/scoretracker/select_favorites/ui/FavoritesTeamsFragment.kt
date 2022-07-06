package com.ignacio.scoretracker.select_favorites.ui

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.ignacio.scoretracker.databinding.FavoriteTeamsScreenFragmentBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavoritesTeamsFragment: Fragment() {

    private lateinit var binding: FavoriteTeamsScreenFragmentBinding
    private val viewModel: FavoriteTeamsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FavoriteTeamsScreenFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
//        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//                activity!!.finish()
//            }
//        })

    }


    private fun setupObservers() {
        viewModel.favoriteLeagues.observe(viewLifecycleOwner) {
            Log.d("SAMBA", "FAVORITES LEAGUES ARE: ${it.size}")
            viewModel.teamsForFavoriteleagues?.observe(viewLifecycleOwner, Observer { teamList ->
                Log.d("SAMBA", "TEAMS OBSERVER")
                if (!teamList.data.isNullOrEmpty()) {
                    Log.d("SAMBA", "TEAMS OBSERVER WITH SIZE ${teamList.data.size}")
                }
            })
        }

    }

}