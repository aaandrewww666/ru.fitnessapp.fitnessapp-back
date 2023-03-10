package ru.fitnessapp.data

import User
import UsersTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import ru.fitnessapp.database.DatabaseFactory.dbQuery
import ru.fitnessapp.utils.ErrorCode
import ru.fitnessapp.utils.ServiceResult

class UserImpl : UserDao {
    private fun resultRowToUser(row: ResultRow) : User {
        return User(
            id = row[UsersTable.id].value,
            userLogin = row[UsersTable.login],
            userName = row[UsersTable.userName],
            passwordHash = row[UsersTable.passwordHash]
        )
    }

    override suspend fun getUser(Id: Int): ServiceResult<User> {
        val result = dbQuery {
            UsersTable.select(UsersTable.id eq Id).singleOrNull()
        }

        return if (result == null) {
            ServiceResult.Error(ErrorCode.NOT_FOUND)
        }
        else ServiceResult.Success(resultRowToUser(result))
        }

    override suspend fun insertUser(user: User): ServiceResult<User> {
        return try {
            dbQuery {
                UsersTable.insert {
                    it[login] = user.userLogin
                    it[userName] = user.userName
                    it[passwordHash] = user.passwordHash
                }.resultedValues?.singleOrNull()?.let{ServiceResult.Success(resultRowToUser(it))
                } ?: ServiceResult.Error(ErrorCode.DATABASE_ERROR)
            }
        } catch (e: Exception) {
            ServiceResult.Error(ErrorCode.DATABASE_ERROR)
        }
    }

    override suspend fun emailExists(login: String): ServiceResult<Boolean> {
        return try {
            val result = dbQuery { UsersTable.select { UsersTable.login eq login }.count() > 0 }
            if (result) ServiceResult.Error(ErrorCode.EMAIL_ALREADY_EXISTS) else ServiceResult
                .Success(true)
        }
        catch (e: Exception) {
            ServiceResult.Error(ErrorCode.DATABASE_ERROR)
        }
    }

    override suspend fun deleteUser(id: Int): ServiceResult<Boolean> {
        return try {
            if (idExists(id)) {
                dbQuery {
                    UsersTable.deleteWhere { UsersTable.id eq id }
                }
                ServiceResult.Success(true)
            } else {
                ServiceResult.Error(ErrorCode.NOT_FOUND)
            }
        }
        catch (e: Exception) {
            ServiceResult.Error(ErrorCode.DATABASE_ERROR)
        }
    }

    private suspend fun idExists(id: Int): Boolean {
        return try {
            val result = dbQuery { UsersTable.select { UsersTable.id eq id }.count() > 0 }
            result
        }
        catch (e: Exception) {
            false
        }
    }
}