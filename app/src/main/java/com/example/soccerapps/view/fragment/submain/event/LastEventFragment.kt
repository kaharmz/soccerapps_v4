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
import com.example.soccerapps.presenter.LastEventPresenter
import com.example.soccerapps.view.adapter.EventAdapter
import com.example.soccerapps.view.interfaces.LastView
import com.example.soccerapps.view.util.EspressoIdlingResource
import com.example.soccerapps.view.util.gone
import com.example.soccerapps.view.util.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_last_event.view.*

/**
 * A simple [Fragment] subclass.
 */
class LastEventFragment : Fragment(), LastView {

    private var events: MutableList<Event> = mutableListOf()
    private lateinit var listEvent: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var lastEventPresenter: LastEventPresenter
    private lateinit var adapter: EventAdapter
    private var leagueId: String? = null

    companion object {
        fun lastInstance(): LastEventFragment =
            LastEventFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_last_event, container, false)
        progressBar = view.last_event_progressbar
        listEvent = view.last_recycler
        leagueId = arguments?.getString("idLeague")
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        listEvent.layoutManager = LinearLayoutManager(context)
        adapter = EventAdapter(events, context)
        listEvent.adapter = adapter
        lastEventPresenter = LastEventPresenter(this, ApiRepository(), Gson())
        EspressoIdlingResource.increment()
        lastEventPresenter.getEventList(leagueId)
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
