package com.example.filmsearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfilePage()
        }
    }
}

data class FilmItem(val title: String, val rating: String, val imageResId: Int)

val Watched  = listOf(
    FilmItem("Ворон", "7.8", R.drawable.voron),
    FilmItem("Битл Джус", "7.5", R.drawable.beetlejuice),
    FilmItem("Тихое место", "8.0", R.drawable.tihoemesto),
    FilmItem("Претеденты", "7.3", R.drawable.pretendents),
    FilmItem("Рай", "7.6", R.drawable.rai),
    FilmItem("Воздух", "8.2", R.drawable.air),
    FilmItem("Субстанция", "7.9", R.drawable.substantion),
    FilmItem("Собиратель душ", "8.1", R.drawable.vsemirnyypotop)
)

@Preview(showBackground = true)
@Composable
fun ProfilePage() {
    Column(modifier = Modifier.padding(16.dp)) {
        Spacer(modifier = Modifier.height(45.dp)) // Отступ сверху
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            item { FilmGenre(title = "Просмотрено", items = Watched) }
        }
    }
}

@Composable
fun FilmGenre(title: String, items: List<FilmItem>) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(title, fontSize = 20.sp, modifier = Modifier.padding(bottom = 4.dp))
            Text(
                "8  >",
                color = Color.Blue,
                modifier = Modifier
                    .clickable { println("Show all clicked for $title") }
                    .padding(bottom = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(modifier = Modifier.padding(bottom = 8.dp)) {
            items(items) { film ->
                FilmItemView(film) {
                    println("Clicked on ${film.rating}")
                }
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
//            Image(
//                painter = painterResource(R.drawable.icons),
//                contentDescription = "Удалить историю",
//                modifier = Modifier
//                    .size(24.dp)
//                    .clickable { println("Show all clicked for $title") }
//            )
            Text(
                "Удалить историю",
                color = Color.Blue,
                modifier = Modifier
                    .clickable { println("Show all clicked for $title") }
                    .padding(top = 4.dp)
            )
        }
    }
}

@Composable
fun FilmItemView(film: FilmItem, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(end = 16.dp)
            .clickable(onClick = onClick)
            .width(100.dp)
    ) {
        Box(modifier = Modifier.padding(start = 10.dp)) {
            Image(
                painter = painterResource(film.imageResId),
                contentDescription = film.title,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .background(Color.Blue, shape = RoundedCornerShape(8.dp))
                    .padding(4.dp)
            ) {
                Text(film.rating, color = Color.White, textAlign = TextAlign.Center)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            film.title,
            fontSize = 14.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            "Драма",
            fontSize = 12.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun CollectionPage() {
    Column(modifier = Modifier.padding(16.dp)) {
        Spacer(modifier = Modifier.height(45.dp))

        Text(
            text = "Коллекция",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "+ Создать свою коллекцию",
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImageWithButton(title = "Любимые", imageRes = R.drawable.favourite)
            ImageWithButton(title = "Избранные", imageRes = R.drawable.izbran)
        }

        Spacer(modifier = Modifier.height(16.dp))

        ImageWithButton(title = "Русские", imageRes = R.drawable.profile)
    }
}

@Composable
fun ImageWithButton(title: String, imageRes: Int) {
    Column(
        modifier = Modifier
            .width(160.dp)
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .wrapContentHeight(Alignment.CenterVertically)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = title,
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = title,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(text = "105", fontSize = 16.sp)
                }
            }
        }
    }
}


