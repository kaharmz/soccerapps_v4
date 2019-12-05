package com.example.soccerapps.presenter

import com.example.soccerapps.TestContextProvider
import com.example.soccerapps.api.ApiRepository
import com.example.soccerapps.model.Event
import com.example.soccerapps.model.EventResponse
import com.example.soccerapps.view.interfaces.LastTeamView
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class LastEventTeamPresenterTest {

    @Mock
    private lateinit var view: LastTeamView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: LastEventTeamPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = LastEventTeamPresenter(
            view, apiRepository, gson,
            TestContextProvider()
        )
    }

    @Test
    fun getEvenTeamList() {
        val result: MutableList<Event> = mutableListOf()
        val idTeam = "133615"
        val response = EventResponse(result, result, result)

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(gson.fromJson("", EventResponse::class.java)).thenReturn(response)

            presenter.getEvenTeamList(idTeam)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showEventTeamList(result)
            Mockito.verify(view).hideLoading()
        }
    }
}