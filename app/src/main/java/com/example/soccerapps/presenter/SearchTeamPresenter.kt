package com.example.soccerapps.presenter

import com.example.soccerapps.api.ApiRepository
import com.example.soccerapps.api.TheSportDBApi
import com.example.soccerapps.model.TeamResponse
import com.example.soccerapps.view.interfaces.SearchTeamView
import com.example.soccerapps.view.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchTeamPresenter(
    private val searchTeamView: SearchTeamView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun searchTeamList(query: String?) {
        searchTeamView.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequestAsync(TheSportDBApi.searchTeam(query)).await(),
                TeamResponse::class.java
            )

            searchTeamView.hideLoading()
            val result = data.team?.filter { it.sport == "Soccer" }
            searchTeamView.showTeamList(result)
        }
    }
}

