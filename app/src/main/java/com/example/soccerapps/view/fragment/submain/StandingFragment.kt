package com.example.soccerapps.view.fragment.submain


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.soccerapps.R
import com.example.soccerapps.api.ApiRepository
import com.example.soccerapps.model.Standing
import com.example.soccerapps.presenter.StandingPresenter
import com.example.soccerapps.view.adapter.StandingAdapter
import com.example.soccerapps.view.interfaces.StandingView
import com.example.soccerapps.view.util.EspressoIdlingResource
import com.example.soccerapps.view.util.gone
import com.example.soccerapps.view.util.visible
import com.google.gson.Gson

/**
 * A simple [Fragment] subclass.
 */
class StandingFragment : Fragment(), StandingView {

    private var table: MutableList<Standing> = mutableListOf()
    private lateinit var listTable: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var standingPresenter: StandingPresenter
    private lateinit var adapter: StandingAdapter
    private var leagueId: String? = null
    private var leagueName: String? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_standing, container, false)
        progressBar = view.findViewById(R.id.standing_progressbar)
        listTable = view.findViewById(R.id.standing_recycler)
        leagueId = arguments?.getString("idLeague")
        leagueName = arguments?.getString("nameLeague")
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.title = leagueName
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        listTable.layoutManager = LinearLayoutManager(context)
        adapter = StandingAdapter(table, context)
        listTable.adapter = adapter
        standingPresenter = StandingPresenter(this, ApiRepository(), Gson())
        EspressoIdlingResource.increment()
        standingPresenter.getTableLeague(leagueId)
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.gone()
    }

    override fun showTableList(data: List<Standing>?) {
        if (!EspressoIdlingResource.idlingResource.isIdleNow) {
            EspressoIdlingResource.decrement()
        }
        table.clear()
        if (data != null) table.addAll(data)
        listTable.adapter = data?.let { StandingAdapter(it, context) }
        adapter.notifyDataSetChanged()
    }

}
