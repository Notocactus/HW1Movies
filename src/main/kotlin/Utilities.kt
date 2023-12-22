import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


const val sessionsFilePath =  "src/main/resources/data/sessions.json"
const val moviesFilePath = "src/main/resources/data/movies.json"
const val ticketsFilePath = "src/main/resources/data/tickets.json"
const val staffFilePath = "src/main/resources/data/staff.json"

const val authoMenu = "Список команд:\n" +
        "1) Войти\n" +
        "2) Зарегистрироваться в системе\n" +
        "0) Выйти из приложения\n" +
        "Введите номер команды: "

const val menu = "Список команд: \n" +
        "1) Продать билет \n" +
        "2) Возврат билета \n" +
        "3) Отметить посещение \n" +
        "4) Добавить фильм \n" +
        "5) Редактировать название фильма \n" +
        "6) Редактировать длительность фильма \n" +
        "7) Удалить фильм \n" +
        "8) Добавить сеанс \n" +
        "9) Редактировать дату сеанса \n" +
        "10) Редактировать цену сеанса \n" +
        "11) Удалить сеанс \n" +
        "12) Показ мест \n" +
        "13) Показать сеансы (по фильму) \n" +
        "14) Показать сеансы (по дате) \n" +
        "15) Показать все фильмы \n" +
        "0) Выйти \n" +
        "Введите номер команды: "

fun isDateValid(date: String?): Boolean {
    val myFormat = SimpleDateFormat("dd/MM/yyyy HH:mm")
    myFormat.isLenient = false
    try {
        myFormat.parse(date)
        val dateToLocalDateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
        return dateToLocalDateTime > LocalDateTime.now()
    } catch (e: Exception) {
        return false
    }
}

fun isDurationValid(input : String?): Boolean {
    val myFormat = SimpleDateFormat("HH:mm")
    myFormat.isLenient = false
    try {
        myFormat.parse(input)
        return true
    } catch (e: Exception) {
        return false
    }
}

fun isUInt(input : String?): Boolean {
    try {
        input!!.toUInt()
        return true
    } catch (e: NumberFormatException) {
        return false
    }
}
