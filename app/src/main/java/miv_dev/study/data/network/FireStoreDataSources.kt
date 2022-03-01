package miv_dev.study.data.network

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.tasks.await
import miv_dev.study.data.UserMapper
import miv_dev.study.domain.entity.User
import javax.inject.Inject


class FireStoreDataSources @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    suspend fun loadUserInfo(firebaseUser: FirebaseUser): User {
        return try {
            val map = firestore.collection("users").document(firebaseUser.uid).get().await()
            UserMapper().mapFirestoreModelToEntity(map.data!!, firebaseUser)
        } catch (e: FirebaseFirestoreException) {
            User(
                firebaseUser.uid,
                firebaseUser.email ?: "",
                firebaseUser.displayName ?: ("User_" + firebaseUser.uid.substring(0, 5)),
                null,
                firebaseUser.isEmailVerified
            ).apply {
                uploadUser(this)
            }
        }
    }

    fun uploadUser(user: User) {
        firestore.collection("users").document(user.uid).update(UserMapper().toMap(user))
    }

}