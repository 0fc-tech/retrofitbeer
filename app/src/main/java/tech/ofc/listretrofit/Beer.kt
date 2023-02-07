package tech.ofc.listretrofit

import com.squareup.moshi.Json

data class Beer(
    val name :String,
    val description: String,
    @Json(name = "abv")
    val alcoolUnit : Float,
    @Json(ignore = true)
    val originCountry : String = "",
    @Json(ignore = true)
    val fermentationType: String= ""
)