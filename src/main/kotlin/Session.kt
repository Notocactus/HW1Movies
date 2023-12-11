import kotlinx.serialization.Serializable

@Serializable
class Session(val id: UInt, var movieId: UInt, var date: String, var cost: UInt) {

}