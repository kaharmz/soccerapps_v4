package com.example.soccerapps.view.interfaces

import com.example.soccerapps.model.Team

interface DetailTeamView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: List<Team>)
}