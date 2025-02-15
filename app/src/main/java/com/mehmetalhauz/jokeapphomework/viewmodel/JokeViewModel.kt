package com.mehmetalhauz.jokeapphomework.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mehmetalhauz.jokeapphomework.model.JokeResponse
import com.mehmetalhauz.jokeapphomework.service.JokeAPI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JokeViewModel(application: Application) : AndroidViewModel(application) {

    var jokeModels = MutableStateFlow<List<JokeResponse>>(emptyList())
    var joke : StateFlow<List<JokeResponse>> = jokeModels

    val BASE_URL = "https://raw.githubusercontent.com/"

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(JokeAPI::class.java)

    init {
        fetchJokes()
    }

    fun fetchJokes(){
        val call = retrofit.getData()
        call.enqueue(object : Callback<List<JokeResponse>> {
            override fun onResponse(
                call: Call<List<JokeResponse>>,
                response: Response<List<JokeResponse>>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        jokeModels.value = it
                    }
                }
            }

            override fun onFailure(call: Call<List<JokeResponse>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}