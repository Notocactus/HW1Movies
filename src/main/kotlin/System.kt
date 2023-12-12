
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.time.LocalDateTime

class System() {
    private var countOfCreatedObjects: Int = 0
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
        var sessions = Json.decodeFromString<Array<Session>>(File(sessionsFile).readText(Charsets.UTF_8))
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
        return ("")
    }

    fun tagVisitor(ticketId: UInt) : String {
        return ("")
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
