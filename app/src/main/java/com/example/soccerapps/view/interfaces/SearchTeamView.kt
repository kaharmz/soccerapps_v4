package com.example.soccerapps.view.interfaces

import com.example.soccerapps.model.Team

interface SearchTeamView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(
        data: List<Team>?
    )
}