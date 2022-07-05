package com.ignacio.scoretracker.utils

import com.ignacio.scoretracker.splash.entities.League

abstract class LeaguesFilters {
    companion object {
        fun filterSoccerLeagues(
            resource : Resource<List<League>>
        ): Resource<List<League>> {
            //TODO HOW TO CHANGE THE VALUE
            var res : Resource<List<League>>? = null
            if(resource.status == Resource.Status.SUCCESS) {
                res = Resource.success(resource.data?.filter {
                    when (it.idLeague) {
                        LeaguesIDs.SPAINLEAGUE1.id,
                        LeaguesIDs.ARGENTINALEAGUE1.id,
                        LeaguesIDs.ARGENTINALEAGUE2.id,
                        LeaguesIDs.DUTCHLEAGUE1.id,
                        LeaguesIDs.ENGLANDLEAGUE1.id,
                        LeaguesIDs.FRANCELEAGUE1.id,
                        LeaguesIDs.FRANCELEAGUE2.id,
                        LeaguesIDs.GERMANYLEAGUE1.id,
                        LeaguesIDs.GERMANYLEAGUE2.id,
                        LeaguesIDs.ITALYLEAGUE1.id,
                        LeaguesIDs.ITALYLEAGUE2.id -> true
                        else -> {
                            false
                        }
                    }
                }!!)
            } else {
                res = resource
            }
            return res
        }
    }
}