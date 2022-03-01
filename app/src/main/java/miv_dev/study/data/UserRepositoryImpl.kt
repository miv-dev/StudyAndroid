package miv_dev.study.data

import android.util.Log
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import miv_dev.study.data.local.db.UserDatabase
import miv_dev.study.data.network.AuthDataSources
import miv_dev.study.data.network.FireStoreDataSources
import miv_dev.study.domain.UserRepository
import miv_dev.study.domain.entity.User
import javax.inject.Inject


class UserRepositoryImpl @Inject constructor(
    private val authDataSources: AuthDataSources,
    private val fireStoreDataSources: FireStoreDataSources,
    private var userDatabase: UserDatabase
) : UserRepository {
    private val mapper = UserMapper()
    private val coroutineScope = CoroutineScope(IO)

    private val _user = MutableSharedFlow<User?>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    override val user = _user.asSharedFlow()


    private val dao by lazy {
        userDatabase.userDao()
    }


    init {
        coroutineScope.launch {
            authDataSources.firebaseUser.collect {
                var user: User? = null
                if (it != null) {
                    try {
                        user = fireStoreDataSources.loadUserInfo(it)
                        dao.updateUser(mapper.mapEntityToDbModel(user))
                    } catch (e: Exception) {
                        Log.e(TAG, "getUser: ${e.message}")
                        user = mapper.mapDbModelToEntity(dao.getUser(it.uid))
                    }
                }
                _user.tryEmit(user)
            }
        }
    }


    override suspend fun updateUser(newUser: User) {
        coroutineScope.launch {
            dao.updateUser(mapper.mapEntityToDbModel(newUser))
            _user.tryEmit(newUser)
            println(newUser.uid)
            fireStoreDataSources.uploadUser(newUser)
        }
    }


    override suspend fun login(email: String, password: String): Result<Unit> {
        authDataSources.login(email, password).fold({
            return Result.success(Unit)
        }, {
            return Result.failure(it)
        })
    }

    override suspend fun register(email: String, password: String): Result<User> {
        authDataSources.register(email, password).fold({ firebaseUser: FirebaseUser ->
            val user = User(
                uid = firebaseUser.uid,
                email = email,
                name = firebaseUser.displayName ?: ("User_" + firebaseUser.uid.substring(0, 5)),
                photoUrl = firebaseUser.displayName,
                emailVerified = firebaseUser.isEmailVerified,
            )
            fireStoreDataSources.uploadUser(user)
            withContext(IO) {
                dao.insertUser(mapper.mapEntityToDbModel(user))
            }
            return Result.success(user)
        }, {
            return Result.failure(it)
        })
    }

    override fun logout() {
        authDataSources.logout()
    }

    companion object {
        const val TAG = "UserRepositoryImpl"
    }
}