package com.example.soccerapps.view.fragment.favorite


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.soccerapps.R
import com.example.soccerapps.db.TeamDB
import com.example.soccerapps.db.database
import com.example.soccerapps.view.adapter.FavoriteTeamAdapter
import kotlinx.android.synthetic.main.fragment_team_favorite.view.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

/**
 * A simple [Fragment] subclass.
 */
class TeamFavoriteFragment : Fragment() {

    private var teamFavorite: MutableList<TeamDB> = mutableListOf()
    private lateinit var listFavorite: RecyclerView
    private lateinit var favoriteTeamAdapter: FavoriteTeamAdapter

    companion object {
        fun teamFavoriteInstance(): TeamFavoriteFragment = TeamFavoriteFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_team_favorite, container, false)
        // Inflate the layout for this fragment
        listFavorite = view.team_recycler_favorite
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        listFavorite.layoutManager = LinearLayoutManager(context)
        favoriteTeamAdapter = FavoriteTeamAdapter(teamFavorite, context)
        listFavorite.adapter = favoriteTeamAdapter
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }

    private fun showFavorite() {
        teamFavorite.clear()
        context?.database?.use {
            val result = select(TeamDB.TABLE_TEAM)
            val favorite = result.parseList(classParser<TeamDB>())
            teamFavorite.addAll(favorite)
            favoriteTeamAdapter.notifyDataSetChanged()
        }
    }
}
