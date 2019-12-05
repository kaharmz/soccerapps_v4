package com.example.soccerapps.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.soccerapps.R
import com.example.soccerapps.model.Event
import com.example.soccerapps.view.activity.DetailEventActivity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_event_team.view.*
import org.jetbrains.anko.startActivity
import java.text.SimpleDateFormat
import java.util.*

class EventTeamAdapter(
    private val event: List<Event>,
    private val context: Context?
) : RecyclerView.Adapter<EventTeamAdapter.EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        EventViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_event_team,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = event.size

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bindItem(event[position])
    }

    class EventViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(event: Event) {
            val timeEvent = event.dateEvent?.let {
                SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(
                    it
                )
            }

            val dateEvent = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault())
                .format(timeEvent)
            itemView.date_event_team.text = dateEvent.toString()
            itemView.league_name_team.text = event.nameLeague
            if (event.homeScore != null) {
                itemView.home_name_team.text = event.nameHomeTeam
                itemView.away_name_team.text = event.nameAwayTeam
                itemView.home_score_team.text = event.homeScore.toString()
                itemView.away_score_team.text = event.awayScore.toString()
            } else {
                itemView.home_score_team.text = "-"
                itemView.away_score_team.text = "-"
                itemView.home_name_team.text = event.nameHomeTeam
                itemView.away_name_team.text = event.nameAwayTeam
            }
            itemView.setOnClickListener {
                itemView.context.startActivity<DetailEventActivity>(
                    "idEvent" to event.idEvent,
                    "idHomeTeam" to event.idHomeTeam,
                    "idAwayTeam" to event.idAwayTeam
                )
            }
        }
    }
}