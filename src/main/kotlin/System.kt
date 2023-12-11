import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class System() {
    private var countOfCreatedObjects: Int = 0
    private fun generateId(): UInt{
        return (LocalDateTime.now().year.toString() + LocalDateTime.now().month.toString() +
                LocalDateTime.now().dayOfMonth.toString() + (countOfCreatedObjects++).toString()).toUInt()

    }

    fun addMovie(name: String, duration: UInt){

    }

    fun editMovie(name: String){

    }

    fun removeMovie(name: String) {

    }

    fun addSession(movieName: String, date: String){

    }

    fun editSession(date: String){

    }

    fun removeSession(date: String){

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
                    return "\nБилет куплен"
                }
                else{
                    return "\nМесто занято, выберите новое"
                }
            }
        }
        return ("\nТакого сеанса не существует. Воспользуйтесь функцией ещё раз с существующим сеансом")
    }

    fun returnTiclet(ticketId: UInt){

    }

    fun tagVisitor(ticketId: UInt){

    }

    fun showPlaces(date: String){

    }

    fun showSessionsByName(movieName: String){

    }

    fun showSessionsByDate(date: String){

    }

    fun showMovies(){

    }
}
