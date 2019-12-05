package com.example.soccerapps.model

import com.google.gson.annotations.SerializedName

class StandingResponse(
    @SerializedName("table")
    val table: List<Standing>
)