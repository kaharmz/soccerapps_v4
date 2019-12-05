package com.example.soccerapps.presenter

import com.example.soccerapps.api.ApiRepository
import com.example.soccerapps.api.TheSportDBApi
import com.example.soccerapps.model.TeamResponse
import com.example.soccerapps.view.interfaces.DetailTeamView
import com.example.soccerapps.view.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailTeamPresenter(
    private val detailTeamView: DetailTeamView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getDetailTeam(teamId: String?) {
        detailTeamView.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequestAsync(
                    TheSportDBApi.getDetailTeam(teamId)
                )
                    .await(), TeamResponse::class.java
            )
            detailTeamView.hideLoading()
            data.team?.let { detailTeamView.showTeamDetail(it) }
        }
    }
}