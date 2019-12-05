package com.example.soccerapps.presenter

import com.example.soccerapps.TestContextProvider
import com.example.soccerapps.api.ApiRepository
import com.example.soccerapps.model.Standing
import com.example.soccerapps.model.StandingResponse
import com.example.soccerapps.view.interfaces.StandingView
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class StandingPresenterTest {

    @Mock
    private lateinit var view: StandingView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiResponse: Deferred<String>

    @Mock
    private lateinit var apiRepository: ApiRepository

    private lateinit var presenter: StandingPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = StandingPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getTableLeague() {
        val table: MutableList<Standing> = mutableListOf()
        val response = StandingResponse(table)
        val idLeague = "4328"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(gson.fromJson("", StandingResponse::class.java)).thenReturn(response)
            presenter.getTableLeague(idLeague)
            Mockito.verify(view).showLoading()
            Mockito.verify(view).showTableList(table)
            Mockito.verify(view).hideLoading()
        }
    }
}