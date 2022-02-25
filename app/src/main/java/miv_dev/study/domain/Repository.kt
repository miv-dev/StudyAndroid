package miv_dev.study.domain

import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import miv_dev.study.domain.entity.User

interface Repository {
    fun getUser(): FirebaseUser?

    val networkConnection: Flow<Boolean>

    suspend fun login(email: String, password: String): Result<User>

    fun logout()
}