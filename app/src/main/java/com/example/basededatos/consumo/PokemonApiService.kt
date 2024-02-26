package com.example.basededatos.consumo

import com.example.basededatos.model.Pokemon
import com.example.basededatos.model.Pokemones
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApiService {
    @GET("pokemon") fun getPokemonById(): Call<Pokemones>

}
