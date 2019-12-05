package com.example.soccerapps.view.activity

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.soccerapps.R
import com.example.soccerapps.api.ApiRepository
import com.example.soccerapps.db.TeamDB
import com.example.soccerapps.db.database
import com.example.soccerapps.model.Team
import com.example.soccerapps.presenter.DetailTeamPresenter
import com.example.soccerapps.view.interfaces.DetailTeamView
import com.example.soccerapps.view.util.EspressoIdlingResource
import com.example.soccerapps.view.util.gone
import com.example.soccerapps.view.util.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class DetailTeamActivity : AppCompatActivity(), DetailTeamView {

    private lateinit var team: Team
    private lateinit var detailTeamPresenter: DetailTeamPresenter
    private var idTeam: String? = null
    private var isFavorite: Boolean = false
    private var menuItem: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)
        idTeam = intent.getStringExtra("idTeam")

        supportActionBar?.title = resources.getString(R.string.team_info_text)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        getTeamDetail()
    }

    private fun getTeamDetail() {
        favoriteState()
        detailTeamPresenter = DetailTeamPresenter(this, ApiRepository(), Gson())
        EspressoIdlingResource.increment()
        detailTeamPresenter.getDetailTeam(idTeam)
    }

    override fun showLoading() {
        progress_bar_team_detail.visible()
        layout_detail_team.gone()
    }

    override fun hideLoading() {
        progress_bar_team_detail.gone()
        layout_detail_team.visible()
    }

    override fun showTeamDetail(data: List<Team>) {
        if (!EspressoIdlingResource.idlingResource.isIdleNow) {
            EspressoIdlingResource.decrement()
        }
        team = data[0]
        bindToView()
    }

    private fun bindToView() {
        team.teamBadge.let { Picasso.get().load(it).into(image_team_detail) }
        name_team_detail.text = team.team
        formed_team_detail.text = team.formedYear
        stadium_name_team_detail.text = team.stadium
        stadium_capacity_team_detail.text = team.stadiumCapacity
        location_stadium_detail.text = team.stadiumLocation
        country_team_detail.text = team.country
        manager_team_detail.text = team.manager
        desc_team_detail.text = team.descriptionEN
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favorite_team, menu)
        menuItem = menu
        setFavorite()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.homes -> {
                finish()
                true
            }

            R.id.add_favorite_team -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()
                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun favoriteState() {
        database.use {
            val result = select(TeamDB.TABLE_TEAM)
                .whereArgs("(TEAM_ID = {id})", "id" to idTeam.toString())
            val favorite = result.parseList(classParser<TeamDB>())
            if (favorite.isNotEmpty()) isFavorite = true
        }
    }

    private fun setFavorite() {
        menuItem?.getItem(0)?.icon =
            if (isFavorite) ContextCompat.getDrawable(this, R.drawable.ic_added_favorite_white)
            else ContextCompat.getDrawable(this, R.drawable.ic_add_favorite_white)
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(
                    TeamDB.TABLE_TEAM,
                    TeamDB.TEAM_ID to team.idTeam,
                    TeamDB.TEAM_NAME to team.team,
                    TeamDB.BADGE_TEAM to team.teamBadge
                )
            }
            toast(resources.getString(R.string.toast_add))
        } catch (e: SQLiteConstraintException) {
            e.localizedMessage
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(
                    TeamDB.TABLE_TEAM, "(TEAM_ID = {id})",
                    "id" to idTeam.toString()
                )
            }
            toast(resources.getString(R.string.toast_remove))
        } catch (e: SQLiteConstraintException) {
            e.localizedMessage
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
