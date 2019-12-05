package com.example.soccerapps.view.fragment.submain


import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.soccerapps.R
import com.example.soccerapps.api.ApiRepository
import com.example.soccerapps.model.Team
import com.example.soccerapps.presenter.TeamPresenter
import com.example.soccerapps.view.activity.SearchTeamActivity
import com.example.soccerapps.view.adapter.TeamAdapter
import com.example.soccerapps.view.interfaces.TeamView
import com.example.soccerapps.view.util.EspressoIdlingResource
import com.example.soccerapps.view.util.gone
import com.example.soccerapps.view.util.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_team.view.*
import org.jetbrains.anko.startActivity

/**
 * A simple [Fragment] subclass.
 */
class TeamFragment : Fragment(), TeamView {

    private var team: MutableList<Team> = mutableListOf()
    private lateinit var listTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var teamPresenter: TeamPresenter
    private lateinit var adapter: TeamAdapter
    private var leagueId: String? = null
    private var leagueName: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_team, container, false)
        setHasOptionsMenu(true)
        leagueId = arguments?.getString("idLeague")
        leagueName = arguments?.getString("nameLeague")
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.title = leagueName
        progressBar = view.team_progressbar
        listTeam = view.team_recycler
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        listTeam.layoutManager = LinearLayoutManager(context)
        adapter = TeamAdapter(team, context)
        listTeam.adapter = adapter
        teamPresenter = TeamPresenter(this, ApiRepository(), Gson())
        EspressoIdlingResource.increment()
        teamPresenter.getAllTeam(leagueId)
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.gone()
    }

    override fun showTeamList(data: List<Team>?) {
        if (!EspressoIdlingResource.idlingResource.isIdleNow) {
            EspressoIdlingResource.decrement()
        }
        team.clear()
        if (data != null) team.addAll(data)
        listTeam.adapter = data?.let { TeamAdapter(it, context) }
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.menu_search_team, menu)
        val searchView = menu.findItem(R.id.search_team)?.actionView as SearchView?
        searchView?.queryHint = resources.getString(R.string.search_team_text)
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                context?.startActivity<SearchTeamActivity>("query" to query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }
}
