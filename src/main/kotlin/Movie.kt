import kotlinx.serialization.Serializable

@Serializable
class Movie(val id: UInt, var name: String, var duration: UInt) {

}