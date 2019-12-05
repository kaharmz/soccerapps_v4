package com.example.soccerapps.presenter

import com.example.soccerapps.api.ApiRepository
import com.example.soccerapps.api.TheSportDBApi
import com.example.soccerapps.model.TeamResponse
import com.example.soccerapps.view.interfaces.TeamView
import com.example.soccerapps.view.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamPresenter(
    private val teamView: TeamView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getAllTeam(leagueId: String?) {
        teamView.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequestAsync(
                    TheSportDBApi.getListTeam(leagueId)
                ).await(), TeamResponse::class.java
            )
            teamView.hideLoading()
            teamView.showTeamList(data.team)
        }
    }
}