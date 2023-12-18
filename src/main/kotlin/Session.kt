import kotlinx.serialization.Serializable

@Serializable
class Session(val id: String, var movieId: String, var date: String, var cost: UInt) {
    var places: Array<Byte> = Array<Byte>(100) { 0 }
}
