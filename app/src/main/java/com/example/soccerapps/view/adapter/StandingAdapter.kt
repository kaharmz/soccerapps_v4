package com.example.soccerapps.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.soccerapps.R
import com.example.soccerapps.model.Standing
import com.example.soccerapps.view.activity.TeamEventActivity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_standing.view.*
import org.jetbrains.anko.startActivity

class StandingAdapter(
    private val table: List<Standing>,
    private val context: Context?
) : RecyclerView.Adapter<StandingAdapter.StandingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        StandingViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_standing,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = table.size

    override fun onBindViewHolder(holder: StandingViewHolder, position: Int) {
        holder.bindItem(table[position])
        holder.itemView.no_standing.text = (position + 1).toString()
    }

    class StandingViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(standing: Standing) {
            itemView.club_name_standing.text = standing.name
            itemView.play_standing.text = standing.played
            itemView.win_standing.text = standing.win
            itemView.draw_standing.text = standing.draw
            itemView.lose_standing.text = standing.loss
            itemView.point_standing.text = standing.total
            itemView.gf_standing.text = standing.goalsFor
            itemView.ga_standing.text = standing.goalsAgainst
            itemView.gd_standing.text = standing.goalsDifference
            itemView.setOnClickListener {
                itemView.context.startActivity<TeamEventActivity>(
                    "idTeam" to standing.teamId,
                    "nameTeam" to standing.name
                )
            }
        }
    }
}