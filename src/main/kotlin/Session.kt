
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.io.File
import java.util.*

@Serializable
class Session(val id: String, var movieId: String, var date: String, var cost: UInt) {
    var places: Array<Byte> = Array<Byte>(100) { 0 }

    fun getPlaces() : String {
        var res = ""
        for (i in 0..99) {
            if (places[i].toInt() == 0) {
                res += (i+1).toString() + ": свободно, "
            }
            else{
                res += (i+1).toString() + ": занято, "
            }
            if ((i + 1).rem(10) == 0){
                res += "\n"
            }
        }
        return res
    }

    override fun toString(): String {
        var movieName = ""
        if (File(moviesFile).exists()){
            var movies = Json.decodeFromString<Array<Movie>>(File(moviesFile).readText(Charsets.UTF_8))
            for (movie in movies){
                if (movie.id.lowercase(Locale.getDefault()) == movieId.lowercase(Locale.getDefault())){
                    movieName = movie.name
                }
            }
        }
        return ("Сеанс фильма '" + movieName + "' в дату: " + date + " стоит " + cost + ". \n")
    }
}
