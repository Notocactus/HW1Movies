import kotlinx.serialization.Serializable

@Serializable
class Ticket(val id: UInt, var sessionId: UInt, var cost: UInt, var place: UInt, var date: String) {
}