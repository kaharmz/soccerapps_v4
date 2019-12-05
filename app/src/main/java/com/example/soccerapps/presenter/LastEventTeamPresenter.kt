package com.example.soccerapps.presenter

import com.example.soccerapps.api.ApiRepository
import com.example.soccerapps.api.TheSportDBApi
import com.example.soccerapps.model.EventResponse
import com.example.soccerapps.view.interfaces.LastTeamView
import com.example.soccerapps.view.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LastEventTeamPresenter(
    private val lastTeamView: LastTeamView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getEvenTeamList(teamId: String?) {
        lastTeamView.showLoading()
        GlobalScope.launch(context.main) {
            val data =
                gson.fromJson(
                    apiRepository.doRequestAsync(
                        TheSportDBApi.getLastEventTeam(teamId)
                    ).await(), EventResponse::class.java
                )
            lastTeamView.hideLoading()
            lastTeamView.showEventTeamList(data.results)
        }
    }
}