
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class System() {
    private var countOfCreatedObjects: Int = 0

    fun clearSessions(localDateTime: LocalDateTime){

    }
    private fun generateId(): String {
        return (LocalDateTime.now().year.toString() + LocalDateTime.now().monthValue.toString() +
                LocalDateTime.now().hour.toString() + LocalDateTime.now().minute.toString() +
                LocalDateTime.now().second.toString() +
                LocalDateTime.now().dayOfMonth.toString() + (countOfCreatedObjects++).toString())

    }

    fun findMovie(name: String) : String {
        var movies: Array<Movie>
        if (File(moviesFile).exists()){
            movies = Json.decodeFromString<Array<Movie>>(File(moviesFile).readText(Charsets.UTF_8))
            for (movie in movies){
                if (movie.name.lowercase(Locale.getDefault()) == name.lowercase(Locale.getDefault())){
                    return movie.id
                }
            }
        }
        return ""
    }

    fun addMovie(name: String, duration: UInt) : String {
        var movies: Array<Movie>
        if (File(moviesFile).exists()){
            movies = Json.decodeFromString<Array<Movie>>(File(moviesFile).readText(Charsets.UTF_8))
            for (movie in movies){
                if (movie.name.lowercase(Locale.getDefault()) == name.lowercase(Locale.getDefault())){
                    return ("Такой фильм уже существует! Добавление отменено.")
                }
            }
            movies += (Movie(generateId(), name, duration))
        }
        else{
            var file = File(moviesFile)
            file.createNewFile()
            movies = arrayOf(Movie(generateId(), name, duration))
        }
        File(moviesFile).writeText(Json.encodeToString<Array<Movie>>(movies))
        return "Фильм успешно добавлен."
    }

    fun editMovie(movieName: String, newName: String) : String {
        if (File(moviesFile).exists()) {
            val movies = Json.decodeFromString<Array<Movie>>(File(moviesFile).readText(Charsets.UTF_8))
            for (movie in movies){
                if (movie.name.lowercase(Locale.getDefault()) == movieName.lowercase(Locale.getDefault())){
                    movie.name = newName
                    File(moviesFile).writeText(Json.encodeToString<Array<Movie>>(movies))
                    return "Название фильма успешно изменено."
                }
            }
        }
        return "Фильма с таким названием не существует!"
    }

    fun editMovie(movieName: String, duration: UInt): String{
        if (File(moviesFile).exists()) {
            val movies = Json.decodeFromString<Array<Movie>>(File(moviesFile).readText(Charsets.UTF_8))
            for (movie in movies){
                if (movie.name.lowercase(Locale.getDefault()) == movieName.lowercase(Locale.getDefault())){
                    movie.duration = duration
                    File(moviesFile).writeText(Json.encodeToString<Array<Movie>>(movies))
                    return "Продолжительность фильма успешно изменена"
                }
            }
        }
        return "Фильма с таким названием не существует!"
    }

    fun removeMovie(name: String) : String {
        if (File(moviesFile).exists()) {
            val movies = Json.decodeFromString<Array<Movie>>(File(moviesFile).readText(Charsets.UTF_8)).toMutableList()
            for (movie in movies){
                if (movie.name.lowercase(Locale.getDefault()) == name.lowercase(Locale.getDefault())){
                    movies -= movie
                    val moviesArr = movies.toTypedArray()
                    File(moviesFile).writeText(Json.encodeToString<Array<Movie>>(moviesArr))
                    return "Фильм успешно удалён из списка"
                }
            }
        }
        return "Фильма с таким названием не существует!"
    }

    fun addSession(movieName: String, date: String, cost: UInt) : String {
        val currMovieId = findMovie(movieName)
        if (currMovieId == "") {
            return ("В каталоге нет такого фильма.")
        }
        var sessions : Array<Session>
        if (File(sessionsFile).exists()) {
            sessions = Json.decodeFromString<Array<Session>>(File(moviesFile).readText(Charsets.UTF_8))
            for (session in sessions){
                if (session.date == date){
                    return ("Это время уже занято.")
                }
            }
            sessions += (Session(generateId(), currMovieId, date, cost))
        }
        else {
            var file = File(sessionsFile)
            file.createNewFile()
            sessions = arrayOf(Session(generateId(), currMovieId, date, cost))
        }
        File(moviesFile).writeText(Json.encodeToString<Array<Session>>(sessions))
        return "Сеанс успешно добавлен."
    }

    fun editSessionDate(date: String, newDate: String) : String {
        return ("")
    }

    fun editSessionCost(date: String, newCost: UInt) : String {
        return ("")
    }

    fun removeSession(date: String) : String {
        return ("")
    }

    fun sellTicket(date: String, place: UInt): String{
        val sessions = Json.decodeFromString<Array<Session>>(File(sessionsFile).readText(Charsets.UTF_8))
        for (session in sessions){
            if (session.date == date){
                if (session.places[place.toInt() - 1] == 0.toByte()){
                    ++(session.places[place.toInt() - 1])
                    var tickets = Json.decodeFromString<Array<Ticket>>(File(ticketsFile).readText(Charsets.UTF_8))
                    tickets += (Ticket(generateId(), session.id, session.cost, place, date))
                    File(ticketsFile).writeText(Json.encodeToString<Array<Ticket>>(tickets))
                    return ("Билет куплен")
                }
                else{
                    return "Место занято, выберите новое"
                }
            }
        }
        return ("Такого сеанса не существует. Воспользуйтесь функцией ещё раз с существующим сеансом")
    }

    fun returnTicket(ticketId: String) : String {
        val tickets = Json.decodeFromString<Array<Ticket>>(File(ticketsFile).readText(Charsets.UTF_8)).toMutableList()
        for (ticket in tickets){
            if (ticket.id == ticketId){
                tickets -= ticket
                val ticketsArr = tickets.toTypedArray()
                File(ticketsFile).writeText(Json.encodeToString<Array<Ticket>>(ticketsArr))
                return "Билет возвращён"
            }
        }
        return ("Билета с данным ID не существует, повторите попытку с корректным билетом")
    }

    fun tagVisitor(ticketId: String) : String {
        val tickets = Json.decodeFromString<Array<Ticket>>(File(ticketsFile).readText(Charsets.UTF_8))
        for (ticket in tickets){
            if (ticket.id == ticketId){
                val sessionID = ticket.sessionId
                val sessions = Json.decodeFromString<Array<Session>>(File(sessionsFile).readText(Charsets.UTF_8))
                for (session in sessions){
                    if (sessionID == session.id){
                        val movieId = session.movieId
                        val movies = Json.decodeFromString<Array<Movie>>(File(moviesFile).readText(Charsets.UTF_8))
                        for (movie in movies){
                            if (movieId == movie.id){
                                var dateStart = LocalDateTime.parse(session.date, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                                val dateEnd = dateStart.plusSeconds( movie.duration.toLong());
                                dateStart = dateStart.minusMinutes(15)
                                val currDate = LocalDateTime.now()
                                if (currDate < dateStart) {
                                    return "Отметка происходит за 15 минут до начала фильма. Попробуйте позже"
                                }
                                if (currDate >= dateEnd)
                                    return "Данный сеанс закончился, вы не успели."
                                }
                            if(session.places[ticket.place.toInt() - 1] == 2.toByte()){
                                return "Человек уже отмечен"
                            }
                            if(session.places[ticket.place.toInt() - 1] == 1.toByte()){
                                return "Человек успешно отмечен"
                            }
                        }
                    }
                }
            }
        }
        return ("Билета с данным ID не существует, повторите попытку с корректным билетом")
    }

    fun showPlaces(date: String) : String {
        return ("")
    }

    fun showSessionsByName(movieName: String) : String {
        return ("")
    }

    fun showSessionsByDate(date: String) : String {
        return ("")
    }

    fun showMovies() : String {
        var moviesString: String = ""
        val movies = Json.decodeFromString<Array<Movie>>(File(moviesFile).readText(Charsets.UTF_8))
        var count: Int = 1
        for (movie in movies){
            moviesString += ("${count}. ${movie.name}. Продолжительность показа: ${movie.duration / 60u}: ${movie.duration - movie.duration / 60u * 60u}\n" )
            count += 1
        }
        return moviesString
    }
}
