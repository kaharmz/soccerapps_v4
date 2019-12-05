package com.example.soccerapps.view.activity

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.soccerapps.R
import com.example.soccerapps.api.ApiRepository
import com.example.soccerapps.model.Team
import com.example.soccerapps.presenter.SearchTeamPresenter
import com.example.soccerapps.view.adapter.TeamAdapter
import com.example.soccerapps.view.interfaces.SearchTeamView
import com.example.soccerapps.view.util.EspressoIdlingResource
import com.example.soccerapps.view.util.gone
import com.example.soccerapps.view.util.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_search_team.*

class SearchTeamActivity : AppCompatActivity(), SearchTeamView {

    private var team: MutableList<Team> = mutableListOf()
    private lateinit var searchTeamPresenter: SearchTeamPresenter
    private lateinit var teamAdapter: TeamAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_team)
        supportActionBar?.title = resources.getString(R.string.search_team_text)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        teamAdapter = TeamAdapter(team, this)
        val query = intent.getStringExtra("query")
        if (query != null) {
            searchTeamPresenter = SearchTeamPresenter(this, ApiRepository(), Gson())
            EspressoIdlingResource.increment()
            searchTeamPresenter.searchTeamList(query)
        }
    }

    override fun showLoading() {
        progress_search_team.visible()
        recycler_search_team.gone()
    }

    override fun hideLoading() {
        progress_search_team.gone()
        recycler_search_team.visible()
    }

    override fun showTeamList(data: List<Team>?) {
        if (!EspressoIdlingResource.idlingResource.isIdleNow) {
            EspressoIdlingResource.decrement()
        }
        team.clear()
        if (data != null) team.addAll(data)
        else error_message_team.visible()
        recycler_search_team.layoutManager = LinearLayoutManager(this)
        recycler_search_team.adapter = data?.let { TeamAdapter(it, this) }
        teamAdapter.notifyDataSetChanged()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_search_team_list, menu)
        val searchView = menu?.findItem(R.id.search_team_list)?.actionView as SearchView?
        searchView?.queryHint = resources.getString(R.string.search_team_text)
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                error_message_team.gone()
                EspressoIdlingResource.increment()
                searchTeamPresenter.searchTeamList(newText)
                return false
            }
        })
        return true
    }
}
