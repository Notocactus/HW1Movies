
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class System() {
    private var countOfCreatedObjects: Int = 0

    fun clearSessions(localDateTime: LocalDateTime){

    }
    private fun generateId(): UInt{
        return (LocalDateTime.now().year.toString() + LocalDateTime.now().month.toString() +
                LocalDateTime.now().hour.toString() + LocalDateTime.now().minute.toString() +
                LocalDateTime.now().second.toString() +
                LocalDateTime.now().dayOfMonth.toString() + (countOfCreatedObjects++).toString()).toUInt()

    }

    fun addMovie(name: String, duration: UInt) : String {
        return ("")
    }

    fun editMovie(name: String) : String {
        return ("")
    }

    fun removeMovie(name: String) : String {
        return ("")
    }

    fun addSession(movieName: String, date: String) : String {
        return ("")
    }

    fun editSession(date: String) : String {
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

    fun returnTicket(ticketId: UInt) : String {
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

    fun tagVisitor(ticketId: UInt) : String {
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
        return ("")
    }
}
