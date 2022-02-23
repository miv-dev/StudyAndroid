package miv_dev.study.data

import com.google.firebase.auth.FirebaseUser
import miv_dev.study.data.network.AuthDataSources
import miv_dev.study.domain.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val authDataSources: AuthDataSources
) : Repository {

    override fun getUser(): FirebaseUser? = authDataSources.getUser()

}