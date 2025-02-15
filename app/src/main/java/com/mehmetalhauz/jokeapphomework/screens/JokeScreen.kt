package com.mehmetalhauz.jokeapphomework.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mehmetalhauz.jokeapphomework.model.JokeResponse

@Composable
fun JokeList(jokes : List<JokeResponse>){
    LazyColumn(
        contentPadding = PaddingValues(5.dp),
        modifier = Modifier.background(color = Color.Black)) {
        items(jokes){ joke ->
            JokeRow(joke = joke)
        }
    }
}

@Composable
fun JokeRow(joke : JokeResponse){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clip(shape = RoundedCornerShape(15.dp))
            .background(color = MaterialTheme.colorScheme.inversePrimary)
            .padding(5.dp)
    ) {
        if(joke.type == "single"){
            // Single Joke
            Text(
                text = joke.joke ?: "Saka bulunamadı",
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
                modifier = Modifier.padding(2.dp),
                fontWeight = FontWeight.SemiBold
            )
        } else if (joke.type == "twopart"){
            // Two-Part Joke
            Text(
                text = joke.setup ?: "Setup bulunamadı",
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
                modifier = Modifier.padding(2.dp),
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = joke.delivery ?: "Cevap bulunamadı",
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
                modifier = Modifier.padding(2.dp),
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}