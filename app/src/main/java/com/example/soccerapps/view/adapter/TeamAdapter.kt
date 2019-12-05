package com.example.soccerapps.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.soccerapps.R
import com.example.soccerapps.model.Team
import com.example.soccerapps.view.activity.DetailTeamActivity
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_team.*
import kotlinx.android.synthetic.main.item_team.view.*
import org.jetbrains.anko.startActivity

class TeamAdapter(
    private val team: List<Team>,
    private val context: Context?
) : RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TeamViewHolder(LayoutInflater.from(context).inflate(R.layout.item_team, parent, false))

    override fun getItemCount(): Int = team.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(team[position])
    }

    class TeamViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(team: Team) {
            itemView.team_name_item.text = team.team
            team.teamBadge.let { Picasso.get().load(it).fit().into(team_image_item) }
            itemView.setOnClickListener {
                itemView.context.startActivity<DetailTeamActivity>(
                    "idTeam" to team.idTeam
                )
            }
        }
    }
}