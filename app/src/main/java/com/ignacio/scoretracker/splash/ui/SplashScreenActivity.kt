package com.ignacio.scoretracker.splash.ui

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ignacio.scoretracker.databinding.SplashScreenBinding
import com.ignacio.scoretracker.select_favorites.FavoritesActivity
import com.ignacio.scoretracker.splash.entities.LeagueDetails
import com.ignacio.scoretracker.splash.entities.LeagueListDetails
import com.ignacio.scoretracker.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {

    private val viewModel: SplashScreenViewModel by viewModels()
    private var lastList : ArrayList<LeagueDetails>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: SplashScreenBinding = SplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.leaguesDetails.observe(this, Observer {
            if (it.status == Resource.Status.SUCCESS &&
                    it.data != null && (lastList == null || !it.data.containsAll(lastList!!))) {
                lastList = ArrayList(it.data)
                Log.d("SAMBA", "ANTES DEL INTENT lastList size: ${lastList?.size}")
                val intent = Intent(this, FavoritesActivity::class.java)
                startActivity(intent)
            }
        })
    }
}