package com.mehmetalhauz.jokeapphomework.service

import com.mehmetalhauz.jokeapphomework.model.JokeResponse
import retrofit2.Call
import retrofit2.http.GET

interface JokeAPI {
    @GET("atilsamancioglu/ProgrammingJokesJSON/refs/heads/main/jokes.json")
    fun getData() : Call<List<JokeResponse>>
}