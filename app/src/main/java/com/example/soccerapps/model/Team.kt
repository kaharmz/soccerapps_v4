package com.example.soccerapps.model

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("idLeague")
    var idLeague: String? = null,

    @SerializedName("idSoccerXML")
    var idSoccerXML: String? = null,

    @SerializedName("idTeam")
    var idTeam: String? = null,

    @SerializedName("intFormedYear")
    var formedYear: String? = null,

    @SerializedName("strSport")
    var sport: String? = null,

    @SerializedName("intLoved")
    var loved: String? = null,

    @SerializedName("intStadiumCapacity")
    var stadiumCapacity: String? = null,

    @SerializedName("strAlternate")
    var alternate: String? = null,

    @SerializedName("strCountry")
    var country: String? = null,

    @SerializedName("strDescriptionEN")
    var descriptionEN: String? = null,

    @SerializedName("strDivision")
    var division: String? = null,

    @SerializedName("strLeague")
    var league: String? = null,

    @SerializedName("strLocked")
    var strLocked: String? = null,

    @SerializedName("strManager")
    var manager: String? = null,

    @SerializedName("strStadium")
    var stadium: String? = null,

    @SerializedName("strStadiumDescription")
    var stadiumDescription: String? = null,

    @SerializedName("strStadiumLocation")
    var stadiumLocation: String? = null,

    @SerializedName("strStadiumThumb")
    var stadiumThumb: String? = null,

    @SerializedName("strTeam")
    var team: String? = null,

    @SerializedName("strTeamBadge")
    var teamBadge: String? = null,

    @SerializedName("strTeamFanart1")
    var teamFanArt: String? = null,

    @SerializedName("strTeamJersey")
    var teamJersey: String? = null,

    @SerializedName("strTeamLogo")
    var teamLogo: String? = null,

    @SerializedName("strTeamShort")
    var teamShort: String? = null,

    @SerializedName("strWebsite")
    var strWebsite: String? = null
)