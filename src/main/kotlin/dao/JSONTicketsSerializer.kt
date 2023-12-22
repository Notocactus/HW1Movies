package dao

import entities.Ticket
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class JSONTicketsSerializer {
    fun jsonSerialize(path: String, array: Array<Ticket>) {
        if (!File(path).exists()){
            File(path).createNewFile()
        }
        File(path).writeText(Json.encodeToString<Array<Ticket>>(array))
    }

    fun jsonDeserialize(path: String): Array<Ticket>?{
        if (File(path).exists()){
            val array = Json.decodeFromString<Array<Ticket>>(File(path).readText(Charsets.UTF_8))
            return array
        }
        return null
    }
}