package org.example

import System
import files
import menu
import java.io.File
import java.text.SimpleDateFormat
import java.time.LocalDateTime


fun isDateValid(date: String?): Boolean {
    val myFormat = SimpleDateFormat("dd/MM/yyyy HH:mm")
    myFormat.isLenient = false
    try {
        myFormat.parse(date)
        return true
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
        var uInt : UInt = input!!.toUInt()
        return true
    } catch (e: NumberFormatException) {
        return false
    }
}

fun CheckCreate(files: Array<String>) {
    for (item in files) {
        var file = File(item)
        file.createNewFile()
    }
}

fun main() {

    CheckCreate(files)

    val system: System = System()

    var command : String = ""
    while (true) {
        system.clearSessions(LocalDateTime.now())

        println(menu)
        command = readln()
        when (command) {
            "0" -> break

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
                        if (placeNum > 1u && placeNum <= 100u) {
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
                var input : String = readln()
                while (!isUInt(input)) {
                    println("Введите корректное id: ")
                    input = readln()
                }
                var ticketId : UInt = input.toUInt()
                println(system.returnTicket(ticketId))
            }

            "3" -> {
                println("Введите id билета посетителя: ")
                var input : String = readln()
                while (!isUInt(input)) {
                    println("Введите корректное id: ")
                    input = readln()
                }
                var ticketId : UInt = input.toUInt()
                println(system.tagVisitor(ticketId))
            }

            "4" -> {
                println("Введите название фильма: ")
                var movieName : String = readln()
                println("Введите длительность фильма (в минутах или в формате HH:mm): ")
                var input : String = readln()
                while (!isDurationValid(input) && !isUInt(input)) {
                    println("Введите корректную длительность фильма (в минутах или в формате HH:mm): ")
                    input = readln()
                }
                var duration : UInt = input.toUInt()
                println(system.addMovie(movieName, duration))
            }

            "5" -> {
                println("Введите название фильма, который хотите изменить: ")
                var movieName : String = readln()
                println(system.editMovie(movieName))
            }

            "6" -> {
                println("Введите название фильма, который хотите удалить: ")
                var movieName : String = readln()
                println(system.removeMovie(movieName))
            }

            "7" -> {
                println("Введите название фильма: ")
                var movieName : String = readln()
                println("Введите дату и время сеанса (DD/MM/YYYY HH:MM): ")
                var sessionDate : String = readln()
                while (!isDateValid(sessionDate)) {
                    println("Пожалуйста введите корректную дату: ")
                    sessionDate = readln()
                }
                println(system.addSession(movieName, sessionDate))
            }

            "8" -> {
                println("Введите дату и время (DD/MM/YYYY HH:MM) сеанса, который вы хотите изменить: ")
                var sessionDate : String = readln()
                while (!isDateValid(sessionDate)) {
                    println("Пожалуйста введите корректную дату: ")
                    sessionDate = readln()
                }
                println(system.editSession(sessionDate))
            }

            "9" -> {
                println("Введите дату и время (DD/MM/YYYY HH:MM) сеанса, который вы хотите удалить: ")
                var sessionDate : String = readln()
                while (!isDateValid(sessionDate)) {
                    println("Пожалуйста введите корректную дату: ")
                    sessionDate = readln()
                }
                println(system.removeSession(sessionDate))
            }

            "10" -> {
                println("Введите дату и время (DD/MM/YYYY HH:MM) сеанса, места которого вы хотите посмотреть: ")
                var sessionDate : String = readln()
                while (!isDateValid(sessionDate)) {
                    println("Пожалуйста введите корректную дату: ")
                    sessionDate = readln()
                }
                println(system.showPlaces(sessionDate))
            }

            "11" -> {
                println("Введите название фильма: ")
                var movieName : String = readln()
                println(system.showSessionsByName(movieName))
            }

            "12" -> {
                println("Введите дату и время (DD/MM/YYYY HH:MM): ")
                var sessionDate : String = readln()
                while (!isDateValid(sessionDate)) {
                    println("Пожалуйста введите корректную дату: ")
                    sessionDate = readln()
                }
                println(system.showSessionsByDate(sessionDate))
            }

            "13" -> {
                println("Полный список фильмов: ")
                println(system.showMovies())
            }

            else -> {
                println("\nВведена неверная команда. Пожалуйста, выберите одну из представленных вариантов. " +
                        "\nНажмите Enter чтобы вернуться к меню. ")
                readln()
//                Thread.sleep(750)
            }
        }
    }
}