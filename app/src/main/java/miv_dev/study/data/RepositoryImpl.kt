package miv_dev.study.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import miv_dev.study.domain.Repository

class RepositoryImpl : Repository {
    private var auth: FirebaseAuth = Firebase.auth

    override fun getUser(): FirebaseUser? = auth.currentUser

}