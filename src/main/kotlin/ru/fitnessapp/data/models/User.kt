import org.jetbrains.exposed.dao.id.IntIdTable
import java.io.Serializable

@kotlinx.serialization.Serializable
data class User(
    val id: Int,
    val userLogin : String,
    val userName: String,
    val passwordHash: String
) : Serializable

object UsersTable : IntIdTable("users") {
    val login = varchar("login",45)
    val passwordHash = varchar("password",45)
    val userName = varchar("userName",45)
}