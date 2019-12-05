package com.example.soccerapps.presenter

import com.example.soccerapps.TestContextProvider
import com.example.soccerapps.api.ApiRepository
import com.example.soccerapps.model.Team
import com.example.soccerapps.model.TeamResponse
import com.example.soccerapps.view.interfaces.DetailTeamView
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailTeamPresenterTest {

    @Mock
    private lateinit var view: DetailTeamView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: DetailTeamPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailTeamPresenter(
            view, apiRepository, gson,
            TestContextProvider()
        )
    }

    @Test
    fun getDetailTeam() {
        val team: MutableList<Team> = mutableListOf()
        val response = TeamResponse(team)
        val idTeam = "133615"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(gson.fromJson("", TeamResponse::class.java)).thenReturn(response)

            presenter.getDetailTeam(idTeam)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showTeamDetail(team)
            Mockito.verify(view).hideLoading()
        }
    }
}