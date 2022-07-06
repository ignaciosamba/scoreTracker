package com.ignacio.scoretracker.select_favorites

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarItemView
import com.google.android.material.navigation.NavigationBarView
import com.ignacio.scoretracker.R
import com.ignacio.scoretracker.databinding.FavoriteScreenBinding
import com.ignacio.scoretracker.select_favorites.ui.FavoritesLeagueFragment
import com.ignacio.scoretracker.select_favorites.ui.FavoritesLeagueFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

@AndroidEntryPoint
class FavoritesActivity : AppCompatActivity() {

    private lateinit var navBottomController: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: FavoriteScreenBinding = FavoriteScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController: NavController = navHostFragment.navController

        val appBarConfiguration: AppBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        navBottomController = binding.bottomNavigationView
        binding.bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.favoritesLeagueFragment -> showBottomNav()
                R.id.favoritesTeamsFragment -> hideBottomNav()
                else -> hideBottomNav()
            }
        }
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.football -> {
                    val action = FavoritesLeagueFragmentDirections.changeSportLeague("Soccer")
                    navController.navigate(action)
                    true
                }
                R.id.basketball -> {
                    val action = FavoritesLeagueFragmentDirections.changeSportLeague("Basketball")
                    navController.navigate(action)
                    true
                }
                else -> {
                    val action = FavoritesLeagueFragmentDirections.changeSportLeague("Others")
                    navController.navigate(action)
                    true
                }
            }
        }
    }


    private fun showBottomNav() {
        navBottomController.visibility = View.VISIBLE
    }

    private fun hideBottomNav() {
        navBottomController.visibility = View.GONE
    }

}