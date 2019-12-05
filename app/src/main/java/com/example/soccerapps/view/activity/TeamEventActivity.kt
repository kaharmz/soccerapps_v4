package com.example.soccerapps.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.soccerapps.R
import com.example.soccerapps.view.adapter.ViewPageAdapter
import com.example.soccerapps.view.fragment.submain.team.LastTeamEventFragment
import com.example.soccerapps.view.fragment.submain.team.NextTeamEventFragment
import kotlinx.android.synthetic.main.activity_team_event.*

class TeamEventActivity : AppCompatActivity() {

    private var idTeam: String? = null
    private var nameTeam: String? = null
    private lateinit var bundle: Bundle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_event)
        idTeam = intent.getStringExtra("idTeam")
        nameTeam = intent.getStringExtra("nameTeam")
        supportActionBar?.title = nameTeam
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        bundle = Bundle()
        bundle.putString("idTeam", idTeam)
        setViewPager(view_pager_event_team)
        tab_event_team.setupWithViewPager(view_pager_event_team)
    }

    private fun setViewPager(viewPager: ViewPager?) {
        val adapter = fragmentManager?.let { ViewPageAdapter(supportFragmentManager) }
        val last = LastTeamEventFragment.lastTeamInstance()
        last.arguments = bundle
        adapter?.addFragment(last, getString(R.string.last_match_text))
        val next = NextTeamEventFragment.nextTeamInstance()
        next.arguments = bundle
        adapter?.addFragment(next, getString(R.string.next_match_text))
        viewPager?.adapter = adapter
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
