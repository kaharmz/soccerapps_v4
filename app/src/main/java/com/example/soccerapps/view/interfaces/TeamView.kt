package com.example.soccerapps.view.interfaces

import com.example.soccerapps.model.Team

interface TeamView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>?)
}