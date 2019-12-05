package com.example.soccerapps.view.fragment.submain.team


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.soccerapps.R
import com.example.soccerapps.api.ApiRepository
import com.example.soccerapps.model.Event
import com.example.soccerapps.presenter.NextEventTeamPresenter
import com.example.soccerapps.view.adapter.EventTeamAdapter
import com.example.soccerapps.view.interfaces.NextTeamView
import com.example.soccerapps.view.util.EspressoIdlingResource
import com.example.soccerapps.view.util.gone
import com.example.soccerapps.view.util.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_next_team_event.view.*

/**
 * A simple [Fragment] subclass.
 */
class NextTeamEventFragment : Fragment(), NextTeamView {

    private var events: MutableList<Event> = mutableListOf()
    private lateinit var listEvent: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var nextEventTeamPresenter: NextEventTeamPresenter
    private lateinit var adapter: EventTeamAdapter
    private var teamId: String? = null

    companion object {
        fun nextTeamInstance(): NextTeamEventFragment =
            NextTeamEventFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_next_team_event, container, false)
        progressBar = view.next_team_event_progressbar
        listEvent = view.next_team_recycler
        teamId = arguments?.getString("idTeam")
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = EventTeamAdapter(events, context)
        listEvent.adapter = adapter
        nextEventTeamPresenter = NextEventTeamPresenter(this, ApiRepository(), Gson())
        EspressoIdlingResource.increment()
        nextEventTeamPresenter.getEvenTeamList(teamId)
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.gone()
    }

    override fun showEventTeamList(data: List<Event>?) {
        if (!EspressoIdlingResource.idlingResource.isIdleNow) {
            EspressoIdlingResource.decrement()
        }
        events.clear()
        if (data != null) events.addAll(data)
        listEvent.layoutManager = LinearLayoutManager(context)
        listEvent.adapter = data?.let { EventTeamAdapter(it, context) }
        adapter.notifyDataSetChanged()
    }

}
