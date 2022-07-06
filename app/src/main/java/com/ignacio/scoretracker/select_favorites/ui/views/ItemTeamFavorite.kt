package com.ignacio.scoretracker.select_favorites.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.ignacio.scoretracker.databinding.ItemTeamFavoriteBinding

class ItemTeamFavorite(context: Context, attrs: AttributeSet? = null)
    : ConstraintLayout(context, attrs) {

    private lateinit var mBinding : ItemTeamFavoriteBinding

    init {
        mBinding = ItemTeamFavoriteBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setData(nome : String?, image : String?) {
        mBinding.nameTeam.text = nome
        Glide.with(mBinding.root)
            .load(image)
            .transform(CenterInside())
            .into(mBinding.imageIconTeam)
    }

    fun setAsFavorite() : Boolean {
        var setAsFavorite = true
        if (mBinding.imageStarTeam.visibility == GONE){
            mBinding.imageStarTeam.visibility = VISIBLE
            setAsFavorite = true
        } else {
            mBinding.imageStarTeam.visibility = GONE
            setAsFavorite = false
        }
        return setAsFavorite
    }
}