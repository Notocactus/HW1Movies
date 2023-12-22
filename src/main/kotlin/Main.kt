import java.time.LocalDateTime

fun main() {
    val system = System()
    var isEnter = false
    var command: String
    while (!isEnter){
        println(authoMenu)
        command = readln()
        when (command){
            "0" -> return
            "1" ->{
                println("Введите логин: ")
                val login = readln()
                println("Введите пароль: ")
                val password = readln()
                if (system.enter(login, password)){
                    println("Авторизация прошла успешно")
                    isEnter = true
                }
                else{
                    println("Пользователя с подобными данными не существует")
                }
            }
            "2" ->{
                println("Введите логин: ")
                var login = readln()
                println("Введите пароль: ")
                var password = readln()
                var isCorrect = true
                if (login == "" || login == "\n" || login == "\t" || password == "" || password == "\n" || password == "\t"){
                    isCorrect = false
                }
                while(!isCorrect){
                    println("Каждое поле не должно представлять из себя пустую строку, Enter или Tab")
                    println("Введите корректный логин: ")
                    login = readln()
                    println("Введите корректный пароль: ")
                    password = readln()
                    if (login != "" && login != "\n" && login != "\t" && password != "" && password != "\n" && password != "\t"){
                        isCorrect = true
                    }
                }
                if (system.register(login, password)){
                    println("Регистрация прошла успешно")
                    isEnter = true
                }
                else{
                    println("Пользователь с такими данными уже существует")
                }
            }
            else -> {
                println("\nВведена неверная команда. Пожалуйста, выберите одну из представленных вариантов. " +
                        "\nНажмите Enter чтобы вернуться к меню. ")
                readln()
            }
        }
    }
    while (true) {
        system.clearSessions(LocalDateTime.now())

        println(menu)
        command = readln()
        when (command) {
            "0" -> return

            "1" -> {
                println("Введите дату и время сеанса (DD/MM/YYYY HH:MM): ")
                var sessionDate : String = readln()
                while (!isDateValid(sessionDate)) {
                    println("Пожалуйста введите корректную дату: ")
                    sessionDate = readln()
                }

                println("Введите номер места")
                var place : String = readln()
                var placeNum : UInt
                while (true) {
                    if (isUInt(place)) {
                        placeNum = place.toUInt()
                        if (placeNum in 1u..100u) {
                            break
                        }
                    }
                    println("Введите корректное место (1, 100): ")
                    place = readln()
                    continue
                }
                println(system.sellTicket(sessionDate, placeNum))
            }

            "2" -> {
                println("Введите id билета, который хотите вернуть: ")
                println(system.returnTicket(readln()))
            }

            "3" -> {
                println("Введите id билета посетителя: ")
                println(system.tagVisitor(readln()))
            }

            "4" -> {
                println("Введите название фильма: ")
                val movieName : String = readln()
                println("Введите длительность фильма в минутах: ")
                var input : String = readln()
                while (!isUInt(input)) {
                    println("Введите корректную длительность фильма в минутах: ")
                    input = readln()
                }
                val duration : UInt = input.toUInt()
                println(system.addMovie(movieName, duration))
            }

            "5" -> {
                println("Введите название фильма, который хотите изменить: ")
                val movieName : String = readln()
                println("Введите новое название фильма: ")
                val newName = readln()
                println(system.editMovie(movieName, newName))
            }

            "6" -> {
                println("Введите название фильма, который хотите изменить: ")
                val movieName : String = readln()
                println("Введите новую длительность фильма: ")
                var input = readln()
                while (!isDurationValid(input) && !isUInt(input)) {
                    println("Введите корректную длительность фильма в минутах: ")
                    input = readln()
                }
                val newDur = input.toUInt()
                println(system.editMovie(movieName, newDur))
            }

            "7" -> {
                println("Введите название фильма, который хотите удалить: ")
                val movieName : String = readln()
                println(system.removeMovie(movieName))
            }

            "8" -> {
                println("Введите название фильма: ")
                val movieName : String = readln()
                println("Введите дату и время сеанса (DD/MM/YYYY HH:MM): ")
                var sessionDate : String = readln()
                while (!isDateValid(sessionDate)) {
                    println("Пожалуйста введите корректную дату: ")
                    sessionDate = readln()
                }
                println("Введите цену билета на сеанс: ")
                var input = readln()
                while (!isUInt(input)) {
                    println("Введите верную цену: ")
                    input = readln()
                }
                val cost = input.toUInt()
                println(system.addSession(movieName, sessionDate, cost))
            }

            "9" -> {
                println("Введите дату и время (DD/MM/YYYY HH:MM) сеанса, который вы хотите изменить: ")
                var sessionDate : String = readln()
                while (!isDateValid(sessionDate)) {
                    println("Пожалуйста введите корректную дату: ")
                    sessionDate = readln()
                }
                println("Введите новую дату и время (DD/MM/YYYY HH:MM) сеанса: ")
                var newDate : String = readln()
                while (!isDateValid(newDate)) {
                    println("Пожалуйста введите корректную дату: ")
                    newDate = readln()
                }
                println(system.editSessionDate(sessionDate, newDate))
            }

            "10" -> {
                println("Введите дату и время (DD/MM/YYYY HH:MM) сеанса, который вы хотите изменить: ")
                var sessionDate : String = readln()
                while (!isDateValid(sessionDate)) {
                    println("Пожалуйста введите корректную дату: ")
                    sessionDate = readln()
                }
                println("Введите цену: ")
                var input = readln()
                while (!isUInt(input)) {
                    println("Введите верную цену: ")
                    input = readln()
                }
                val cost = input.toUInt()
                println(system.editSessionCost(sessionDate, cost))
            }

            "11" -> {
                println("Введите дату и время (DD/MM/YYYY HH:MM) сеанса, который вы хотите удалить: ")
                var sessionDate : String = readln()
                while (!isDateValid(sessionDate)) {
                    println("Пожалуйста введите корректную дату: ")
                    sessionDate = readln()
                }
                println(system.removeSession(sessionDate))
            }

            "12" -> {
                println("Введите дату и время (DD/MM/YYYY HH:MM) сеанса, места которого вы хотите посмотреть: ")
                var sessionDate : String = readln()
                while (!isDateValid(sessionDate)) {
                    println("Пожалуйста введите корректную дату: ")
                    sessionDate = readln()
                }
                println(system.showPlaces(sessionDate))
            }

            "13" -> {
                println("Введите название фильма: ")
                val movieName : String = readln()
                println(system.showSessionsByName(movieName))
            }

            "14" -> {
                println("Введите дату и время (DD/MM/YYYY HH:MM): ")
                var sessionDate : String = readln()
                while (!isDateValid(sessionDate)) {
                    println("Пожалуйста введите корректную дату: ")
                    sessionDate = readln()
                }
                println(system.showSessionsByDate(sessionDate))
            }

            "15" -> {
                println("Полный список фильмов: ")
                println(system.showMovies())
            }

            else -> {
                println("\nВведена неверная команда. Пожалуйста, выберите одну из представленных вариантов. " +
                        "\nНажмите Enter чтобы вернуться к меню. ")
                readln()
            }
        }
    }
}