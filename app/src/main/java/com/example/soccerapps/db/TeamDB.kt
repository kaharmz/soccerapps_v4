package com.example.soccerapps.db

class TeamDB(
    val id: Long?,
    val teamId: String?,
    val teamName: String?,
    val badgeTeam: String?
) {

    companion object {
        const val TABLE_TEAM = "TABLE_TEAM"
        const val ID: String = "ID"
        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM_NAME: String = "TEAM_NAME"
        const val BADGE_TEAM: String = "BADGE_TEAM"
    }
}