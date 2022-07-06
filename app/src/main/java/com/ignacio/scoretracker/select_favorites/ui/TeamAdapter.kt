package com.ignacio.scoretracker.select_favorites.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isEmpty
import androidx.recyclerview.widget.RecyclerView
import com.ignacio.scoretracker.R
import com.ignacio.scoretracker.databinding.ItemLeagueTeamFavoriteBinding
import com.ignacio.scoretracker.select_favorites.entities.TeamDetails
import com.ignacio.scoretracker.select_favorites.model.ExpandableFavoriteTeamsModel
import com.ignacio.scoretracker.select_favorites.ui.views.ItemTeamFavorite

class TeamAdapter(private val listener : FavoriteTeamsItemListener)
    : RecyclerView.Adapter<TeamViewHolder>() {

    interface FavoriteTeamsItemListener {
        fun onClickedLeague(teamFavorite: TeamDetails, setAsFavorite : Boolean)
    }

    private val items = ArrayList<ExpandableFavoriteTeamsModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val binding: ItemLeagueTeamFavoriteBinding =
            ItemLeagueTeamFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(items: List<ExpandableFavoriteTeamsModel>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}

class TeamViewHolder(private val itemBinding: ItemLeagueTeamFavoriteBinding,
                       private val listener: TeamAdapter.FavoriteTeamsItemListener)
    : RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {

    private lateinit var league: ExpandableFavoriteTeamsModel

    init {
        itemBinding.root.setOnClickListener(this)
    }

    fun bind(item: ExpandableFavoriteTeamsModel) {
        this.league = item
        itemBinding.leagueName.text = item.leagueName
    }

    override fun onClick(v: View?) {
        //TODO SAMBA we must to check if an item is selected as favorite in the DB to show the star.
        if (itemBinding.teamsLinearlayout.visibility == View.GONE){
            Log.d("SAMBA", "ABRO")
            itemBinding.arrowImage.setImageDrawable(ContextCompat.getDrawable(v!!.context, R.drawable.arrow_up))
            itemBinding.teamsLinearlayout.visibility = View.VISIBLE
            if (itemBinding.teamsLinearlayout.isEmpty()) {
                league.teams.forEach { teamDetails ->
                    val itemView = ItemTeamFavorite(v.context, null)
                    itemView.setData(teamDetails.strTeam, teamDetails.strTeamBadge)
                    itemView.setOnClickListener {
                        val setAsFavorite = itemView.setAsFavorite()
                        listener.onClickedLeague(teamDetails, setAsFavorite)
                    }
                    itemBinding.teamsLinearlayout.addView(itemView)
                }
            }
        } else {
            itemBinding.arrowImage.setImageDrawable(ContextCompat.getDrawable(v!!.context, R.drawable.arrow_down))
            itemBinding.teamsLinearlayout.visibility = View.GONE
            Log.d("SAMBA", "CIERRO")
        }
    }
}

