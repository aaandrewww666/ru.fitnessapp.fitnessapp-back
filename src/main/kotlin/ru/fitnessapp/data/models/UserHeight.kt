import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import java.io.Serializable

@kotlinx.serialization.Serializable
data class UserHeight(
    val id : Int,
    val userId : Int,
    val height : Double
) : Serializable

object UsersHeightTable : IntIdTable("users_height") {
    val userId = integer("user_id")
    val userHeight = double("height")
}