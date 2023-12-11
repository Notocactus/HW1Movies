package org.example

import System
import files
import menu
import java.io.File
import java.text.SimpleDateFormat


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

fun CheckCreate(files: Array<String>) {
    for (item in files) {
        var file = File(item)
        file.createNewFile()
    }
}

fun main() {

//    val dateTime = LocalDateTime.now()
//    println(dateTime.format(DateTimeFormatter.ofPattern("d/M/y H:m:ss")))

    CheckCreate(files)

    val system: System = System()

    var command : String = ""
    while (true) {
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
                    try {
                        placeNum = place.toUInt()
                        if (placeNum == 0u || placeNum > 100u) {
                            throw NumberFormatException()
                        }
                        break
                    } catch (e: NumberFormatException) {
                        println("Введите корректное место (1, 100): ")
                        place = readln()
                        continue
                    }
                }
                system.sellTicket(sessionDate, placeNum)
            }
        }


    }
}