package com.ignacio.scoretracker.select_favorites.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.ignacio.scoretracker.databinding.ItemLeagueFavoriteBinding
import com.ignacio.scoretracker.splash.entities.LeagueDetails

class LeagueAdapter (private val listener: FavoriteLeaguesItemListener)
    : RecyclerView.Adapter<LeagueViewHolder>()  {

    interface FavoriteLeaguesItemListener {
        fun onClickedLeague(league: LeagueDetails, mustSave : Boolean)
    }

    private val items = ArrayList<LeagueDetails>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        val binding: ItemLeagueFavoriteBinding = ItemLeagueFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LeagueViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(items: List<LeagueDetails>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

}

class LeagueViewHolder(private val itemBinding: ItemLeagueFavoriteBinding,
    private val listener: LeagueAdapter.FavoriteLeaguesItemListener)
    : RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {

    private lateinit var leagueDetails: LeagueDetails

    init {
        itemBinding.root.setOnClickListener(this)
    }

    fun bind(item: LeagueDetails) {
        this.leagueDetails = item
        itemBinding.nameCountry.text = item.strCountry
        itemBinding.nameLeague.text = if (item.strLeague.isNullOrEmpty()) item.strLeagueAlternate else item.strLeague
        var visibility = View.GONE
        if (leagueDetails.isSelectedAsFavorite) {
            visibility = View.VISIBLE
        }
        itemBinding.imageStar.visibility = visibility
        Glide.with(itemBinding.root)
            .load(item.strBadge)
            .transform(CenterInside())
            .into(itemBinding.imageIconLeague)
    }

    override fun onClick(v: View?) {
        if(itemBinding.imageStar.visibility == View.GONE) {
            itemBinding.imageStar.visibility = View.VISIBLE
            leagueDetails.isSelectedAsFavorite = true
            listener.onClickedLeague(leagueDetails, true)
        } else {
            itemBinding.imageStar.visibility = View.GONE
            leagueDetails.isSelectedAsFavorite = false
            listener.onClickedLeague(leagueDetails, false)
        }
    }
}