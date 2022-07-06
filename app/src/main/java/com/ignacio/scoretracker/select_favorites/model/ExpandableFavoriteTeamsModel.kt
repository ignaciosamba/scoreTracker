package com.ignacio.scoretracker.select_favorites.model

import com.ignacio.scoretracker.select_favorites.entities.TeamDetails

data class ExpandableFavoriteTeamsModel (
    val leagueName : String,
    val teams : List<TeamDetails>
)