package com.example.soccerapps.api

import com.example.soccerapps.BuildConfig

object TheSportDBApi {

    private const val BASE_URL = "https://private-044be-dicodingfootball.apiary-mock.com/"

    //Last Event
    fun getLastEvent(leagueId: String?): String {
        return BuildConfig.BASE_URL + BuildConfig.PATH + BuildConfig.TSDB_API_KEY + BuildConfig.LAST_EVENT + leagueId
    }

    //Next Event
    fun getNextEvent(leagueId: String?): String {
        return BuildConfig.BASE_URL + BuildConfig.PATH + BuildConfig.TSDB_API_KEY + BuildConfig.NEXT_EVENT + leagueId
    }

    //Detail Event
    fun getDetailEvent(eventId: String?): String {
        return BuildConfig.BASE_URL + BuildConfig.PATH + BuildConfig.TSDB_API_KEY + BuildConfig.DETAIL_EVENT + eventId
    }

    //Home Badge
    fun getHomeBadge(leagueId: String?): String {
        return BuildConfig.BASE_URL + BuildConfig.PATH + BuildConfig.TSDB_API_KEY + BuildConfig.HOME_BADGE + leagueId
    }

    //AwayBadge
    fun getAwayBadge(leagueId: String?): String {
        return BuildConfig.BASE_URL + BuildConfig.PATH + BuildConfig.TSDB_API_KEY + BuildConfig.AWAY_BADGE + leagueId
    }

    //Search Event
    fun searchEvent(query: String?): String {
        return BuildConfig.BASE_URL + BuildConfig.PATH + BuildConfig.TSDB_API_KEY + BuildConfig.SEARCH_EVENT + query
    }

    //Get Detail League
    fun getDetailLeagueById(leagueId: String?): String {
        return BuildConfig.BASE_URL + BuildConfig.PATH + BuildConfig.TSDB_API_KEY + BuildConfig.DETAIL_LEAGUE + leagueId
    }

    //Get Table
    fun getTableById(leagueId: String?): String {
        return BuildConfig.BASE_URL + BuildConfig.PATH + BuildConfig.TSDB_API_KEY + BuildConfig.TABLE_LEAGUE + leagueId + BuildConfig.SEASON
    }

    //Last Event Team
    fun getLastEventTeam(teamId: String?): String {
        return BuildConfig.BASE_URL + BuildConfig.PATH + BuildConfig.TSDB_API_KEY + BuildConfig.LAST_EVENT_TEAM + teamId
    }

    //Next Event Team
    fun getNextEventTeam(teamId: String?): String {
        return BuildConfig.BASE_URL + BuildConfig.PATH + BuildConfig.TSDB_API_KEY + BuildConfig.NEXT_EVENT_TEAM + teamId
    }

    //List Team
    fun getListTeam(leagueId: String?): String {
        return BuildConfig.BASE_URL + BuildConfig.PATH + BuildConfig.TSDB_API_KEY + BuildConfig.LIST_TEAM + leagueId
    }

    //Detail Team
    fun getDetailTeam(teamId: String?): String {
        return BuildConfig.BASE_URL + BuildConfig.PATH + BuildConfig.TSDB_API_KEY + BuildConfig.DETAIL_TEAM + teamId
    }

    //Search Team
    fun searchTeam(query: String?): String {
        return BuildConfig.BASE_URL + BuildConfig.PATH + BuildConfig.TSDB_API_KEY + BuildConfig.SEARCH_TEAM + query
    }

}