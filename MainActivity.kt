import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.myapplication.Film
import androidx.compose.foundation.Image
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import com.example.myapplication.R
import com.example.myapplication.SearchViewModel

@Preview
@Composable
fun SearchScreen(viewModel: SearchViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    var searchQuery by remember { mutableStateOf("") }
    val searchResults by viewModel.searchResults.collectAsState()
    val isLoading by viewModel.loading.collectAsState()
    val error by viewModel.error.collectAsState()

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Search Films") },
                placeholder = { Text("Enter film name...") },
                modifier = Modifier
                    .weight(1f)
                    .background(Color.Gray.copy(alpha = 0.1f), MaterialTheme.shapes.small)
                    .padding(8.dp),
                textStyle = TextStyle(fontSize = 18.sp, color = Color.Black),
                singleLine = true
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = { viewModel.searchFilms(searchQuery, page = 1) },
                enabled = searchQuery.isNotEmpty() && !isLoading
            ) {
                Text("Search")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            if (error.isNotEmpty()) {
                Text("Error: $error", color = Color.Red, style = MaterialTheme.typography.bodyLarge)
            } else {
                if (searchResults.isEmpty()) {
                    Text("No films found", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.align(Alignment.CenterHorizontally))
                } else {
                    LazyColumn {
                        items(searchResults) { film ->
                            FilmItem(film)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FilmItem(film: Film) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.Gray.copy(alpha = 0.1f), MaterialTheme.shapes.small)
            .padding(8.dp)
    ) {
        film.posterUrl?.let {
            val painter = rememberImagePainter(
                data = it,
                builder = {
                    crossfade(true)
                    placeholder(R.drawable.ic_launcher_background)
                    error(R.drawable.ic_launcher_background)
                }
            )
            Image(
                painter = painter,
                contentDescription = "Poster",
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.CenterVertically)
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Column(Modifier.weight(1f).padding(8.dp)) {
            Text(
                text = film.nameRu ?: film.nameEn ?: "Unknown",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            )
            Text(text = "Year: ${film.year ?: "N/A"}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Rating: ${film.rating ?: "N/A"}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
