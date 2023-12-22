package dao

import entities.Staff
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class JSONStuffSerializer {
    fun jsonSerialize(path: String, array: Array<Staff>) {
        if (!File(path).exists()){
            File(path).createNewFile()
        }
        File(path).writeText(Json.encodeToString<Array<Staff>>(array))
    }

    fun jsonDeserialize(path: String): Array<Staff>?{
        if (File(path).exists()){
            val array = Json.decodeFromString<Array<Staff>>(File(path).readText(Charsets.UTF_8))
            return array
        }
        return null
    }
}