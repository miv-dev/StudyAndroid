package miv_dev.study.domain

import kotlinx.coroutines.flow.SharedFlow
import miv_dev.study.domain.entity.User

interface UserRepository {
    val user: SharedFlow<User?>

    suspend fun updateUser(newUser: User)

    suspend fun login(email: String, password: String): Result<Unit>

    suspend fun register(email: String, password: String): Result<User>

    fun logout()
}