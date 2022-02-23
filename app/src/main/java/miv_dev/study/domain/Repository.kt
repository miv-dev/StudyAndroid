package miv_dev.study.domain

import com.google.firebase.auth.FirebaseUser

interface Repository {
    fun getUser(): FirebaseUser?
}