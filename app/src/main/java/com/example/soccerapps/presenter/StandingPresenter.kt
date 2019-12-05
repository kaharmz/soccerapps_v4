package com.example.soccerapps.presenter

import com.example.soccerapps.api.ApiRepository
import com.example.soccerapps.api.TheSportDBApi
import com.example.soccerapps.model.StandingResponse
import com.example.soccerapps.view.interfaces.StandingView
import com.example.soccerapps.view.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StandingPresenter(
    private val standingView: StandingView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getTableLeague(leagueId: String?) {
        standingView.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequestAsync(
                    TheSportDBApi.getTableById(leagueId)
                ).await(), StandingResponse::class.java
            )
            standingView.hideLoading()
            standingView.showTableList(data.table)
        }
    }
}