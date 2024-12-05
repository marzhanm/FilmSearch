import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.myapplication.Film

data class FilmSearchResult(
    val films: List<Film>
)

interface KinopoiskApiService {
    @GET("films")
    suspend fun searchFilmsByKeyword(
        @Header("Authorization") apiKey: String,
        @Query("keyword") keyword: String
    ): FilmSearchResult
}

class KinopoiskApiClient(private val token: String) {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.kinopoisk.ru/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(KinopoiskApiService::class.java)

    suspend fun searchFilms(keyword: String): List<Film> {
        return withContext(Dispatchers.IO) {
            try {
                val result = service.searchFilmsByKeyword(token, keyword)
                result.films
            } catch (e: retrofit2.HttpException) {
                println("HTTP Error: ${e.message()}, Response Code: ${e.code()}")
                emptyList()
            } catch (e: java.net.UnknownHostException) {
                println("Network Error: No internet connection")
                emptyList()
            } catch (e: java.net.SocketTimeoutException) {
                println("Timeout Error: The request timed out")
                emptyList()
            } catch (e: Exception) {
                println("Error searching films: ${e.message}")
                emptyList()
            }
        }
    }
}
