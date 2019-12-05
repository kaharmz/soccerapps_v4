package com.example.soccerapps.view.interfaces

import com.example.soccerapps.model.Standing

interface StandingView {
    fun showLoading()
    fun hideLoading()
    fun showTableList(data: List<Standing>?)
}