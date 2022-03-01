package miv_dev.study.data.network

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthDataSources @Inject constructor(
    private var auth: FirebaseAuth
) {
    private val coroutineScope = CoroutineScope(Default)
    private val _firebaseUser = MutableSharedFlow<FirebaseUser?>()

    val firebaseUser
        get() = _firebaseUser.asSharedFlow()

    init {
        auth.addAuthStateListener {
            coroutineScope.launch {
                _firebaseUser.emit(it.currentUser)
            }
        }
    }

    suspend fun login(email: String, password: String): Result<FirebaseUser> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            if (result.user == null) {
                Result.failure(Exception("error"))
            } else {
                Result.success(result.user!!)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun register(email: String, password: String): Result<FirebaseUser> {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            if (result.user == null) {
                Result.failure(Exception("error"))
            } else {
                Result.success(result.user!!)
            }
        } catch (e: FirebaseAuthException) {
            Result.failure(e)
        }
    }

    fun logout() {
        auth.signOut()
    }
}

