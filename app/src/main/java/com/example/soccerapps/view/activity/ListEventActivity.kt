package com.example.soccerapps.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.soccerapps.R
import com.example.soccerapps.view.fragment.submain.MainEventFragment
import com.example.soccerapps.view.fragment.submain.StandingFragment
import com.example.soccerapps.view.fragment.submain.TeamFragment
import kotlinx.android.synthetic.main.sub_bottom_nav_view.*


class ListEventActivity : AppCompatActivity() {

    private var idLeague: String? = null
    private var nameLeague: String? = null
    private lateinit var bundle: Bundle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_event)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        idLeague = intent.getStringExtra("idLeague")
        nameLeague = intent.getStringExtra("nameLeague")
        bundle = Bundle()
        bundle.putString("idLeague", idLeague)
        bundle.putString("nameLeague", nameLeague)

        sub_bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.standing -> {
                    addStandingFragment(savedInstanceState)
                }

                R.id.event -> {
                    addEventFragment(savedInstanceState)
                }

                R.id.team -> {
                    addTeamFragment(savedInstanceState)
                }
            }

            true
        }
        sub_bottom_navigation.selectedItemId = R.id.standing
    }

    private fun addTeamFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            val team = TeamFragment()
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.sub_main_container,
                    team,
                    TeamFragment::class.java.simpleName
                )
                .commit()
            team.arguments = bundle
        }
    }

    private fun addEventFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            val event = MainEventFragment()
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.sub_main_container,
                    event,
                    MainEventFragment::class.java.simpleName
                )
                .commit()
            event.arguments = bundle
        }
    }

    private fun addStandingFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            val standing = StandingFragment()
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.sub_main_container,
                    standing,
                    StandingFragment::class.java.simpleName
                )
                .commit()
            standing.arguments = bundle
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
