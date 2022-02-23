package miv_dev.study.data.network

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class AuthDataSources @Inject constructor(
    private var auth: FirebaseAuth
) {
    fun getUser(): FirebaseUser? = auth.currentUser

}