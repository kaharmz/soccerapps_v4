package com.example.soccerapps.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.soccerapps.R
import com.example.soccerapps.db.TeamDB
import com.example.soccerapps.view.activity.DetailTeamActivity
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_team.*
import kotlinx.android.synthetic.main.item_team.view.*
import org.jetbrains.anko.startActivity

class FavoriteTeamAdapter(
    private val favoriteTeam: List<TeamDB>,
    private val context: Context?
) : RecyclerView.Adapter<FavoriteTeamAdapter.TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TeamViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_team, parent, false)
        )

    override fun getItemCount(): Int = favoriteTeam.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(favoriteTeam[position])
    }

    class TeamViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(teamDB: TeamDB) {
            itemView.team_name_item.text = teamDB.teamName
            teamDB.badgeTeam?.let { Picasso.get().load(it).fit().into(team_image_item) }
            itemView.setOnClickListener {
                itemView.context.startActivity<DetailTeamActivity>(
                    "idTeam" to teamDB.teamId,
                    "nameTeam" to teamDB.teamName,
                    "badgeTeam" to teamDB.badgeTeam
                )
            }
        }

    }

}