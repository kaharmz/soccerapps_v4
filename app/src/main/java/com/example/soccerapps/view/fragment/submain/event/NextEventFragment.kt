package com.example.soccerapps.view.fragment.submain.event


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
import com.example.soccerapps.presenter.NextEventPresenter
import com.example.soccerapps.view.adapter.EventAdapter
import com.example.soccerapps.view.interfaces.NextView
import com.example.soccerapps.view.util.EspressoIdlingResource
import com.example.soccerapps.view.util.gone
import com.example.soccerapps.view.util.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_next_event.view.*

/**
 * A simple [Fragment] subclass.
 */
class NextEventFragment : Fragment(), NextView {

    private var events: MutableList<Event> = mutableListOf()
    private lateinit var listEvent: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var nextEventPresenter: NextEventPresenter
    private lateinit var adapter: EventAdapter
    private var leagueId: String? = null

    companion object {
        fun nextInstance(): NextEventFragment =
            NextEventFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_next_event, container, false)
        progressBar = view.next_event_progressbar
        listEvent = view.next_recycler

        leagueId = arguments?.getString("idLeague")
        println("Ini data next $leagueId")

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        listEvent.layoutManager = LinearLayoutManager(context)
        adapter = EventAdapter(events, context)
        listEvent.adapter = adapter
        nextEventPresenter = NextEventPresenter(this, ApiRepository(), Gson())
        EspressoIdlingResource.increment()
        nextEventPresenter.getEventList(leagueId)
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.gone()
    }

    override fun showEventList(data: List<Event>?) {
        if (!EspressoIdlingResource.idlingResource.isIdleNow) {
            EspressoIdlingResource.decrement()
        }
        events.clear()
        if (data != null) events.addAll(data)
        listEvent.adapter = data?.let { EventAdapter(it, context) }
        adapter.notifyDataSetChanged()
    }
}
