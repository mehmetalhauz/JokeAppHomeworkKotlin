package com.mehmetalhauz.jokeapphomework

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.mehmetalhauz.jokeapphomework.screens.JokeList
import com.mehmetalhauz.jokeapphomework.ui.theme.JokeAppHomeworkTheme
import com.mehmetalhauz.jokeapphomework.viewmodel.JokeViewModel

class MainActivity : ComponentActivity() {

    private val viewModel : JokeViewModel by viewModels<JokeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JokeAppHomeworkTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)){
                        val jokes by viewModel.joke.collectAsState()
                        JokeList(jokes = jokes)
                    }
                }
            }
        }
    }
}