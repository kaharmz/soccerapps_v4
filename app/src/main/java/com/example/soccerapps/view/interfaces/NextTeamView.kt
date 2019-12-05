package com.example.soccerapps.view.interfaces

import com.example.soccerapps.model.Event

interface NextTeamView {
    fun showLoading()
    fun hideLoading()
    fun showEventTeamList(
        data: List<Event>?
    )
}