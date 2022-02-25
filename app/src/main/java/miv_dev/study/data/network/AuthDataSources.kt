package miv_dev.study.data.network

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import miv_dev.study.domain.entity.User
import javax.inject.Inject

class AuthDataSources @Inject constructor(
    private var auth: FirebaseAuth
) {
    fun getUser(): FirebaseUser? = auth.currentUser

    suspend fun login(email: String, password: String): Result<User> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            if (result.user == null) {
                Result.failure(Exception("error"))
            } else {
                Result.success(User(result.user!!.uid, result.user!!.displayName))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun logout() {
        auth.signOut()
    }
}

