package dao

import entities.Session
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class JSONSessionsSerializer {
    fun jsonSerialize(path: String, array: Array<Session>) {
        if (!File(path).exists()){
            File(path).createNewFile()
        }
        File(path).writeText(Json.encodeToString<Array<Session>>(array))
    }

    fun jsonDeserialize(path: String): Array<Session>?{
        if (File(path).exists()){
            val array = Json.decodeFromString<Array<Session>>(File(path).readText(Charsets.UTF_8))
            return array
        }
        return null
    }
}