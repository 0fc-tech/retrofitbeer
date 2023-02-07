package tech.ofc.listretrofit

import retrofit2.Call
import retrofit2.http.GET

interface IBeerService {
    @GET("beers")
    fun listBeer(): Call<List<Beer>>
}

