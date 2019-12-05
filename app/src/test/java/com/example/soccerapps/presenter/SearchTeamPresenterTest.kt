package com.example.soccerapps.presenter

import com.example.soccerapps.TestContextProvider
import com.example.soccerapps.api.ApiRepository
import com.example.soccerapps.model.Team
import com.example.soccerapps.model.TeamResponse
import com.example.soccerapps.view.interfaces.SearchTeamView
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SearchTeamPresenterTest {

    @Mock
    private lateinit var view: SearchTeamView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>


    private lateinit var presenter: SearchTeamPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = SearchTeamPresenter(
            view, apiRepository, gson,
            TestContextProvider()
        )
    }

    @Test
    fun searchTeamList() {
        val team: MutableList<Team> = mutableListOf()
        val query = "Napoli"
        val response = TeamResponse(team)

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(gson.fromJson("", TeamResponse::class.java)).thenReturn(response)

            presenter.searchTeamList(query)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showTeamList(team)
            Mockito.verify(view).hideLoading()
        }
    }
}