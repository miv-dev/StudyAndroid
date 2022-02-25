package miv_dev.study.data

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import miv_dev.study.data.network.AuthDataSources
import miv_dev.study.domain.Repository
import miv_dev.study.domain.entity.User
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val authDataSources: AuthDataSources,
    private val connectivityManager: ConnectivityManager
) : Repository {

    private val networkRequest: NetworkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
//        .addCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .build()

    override fun getUser(): FirebaseUser? = authDataSources.getUser()


    @OptIn(ExperimentalCoroutinesApi::class)
    override val networkConnection: Flow<Boolean> = channelFlow {
        send(connectivityManager.activeNetwork != null)

        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                println("connected")
                trySend(true)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                println("disconnected")
                trySend(false)
            }

            override fun onUnavailable() {
                super.onUnavailable()
                trySend(false)
            }

        }

        connectivityManager.requestNetwork(
            networkRequest, networkCallback
        )
        awaitClose { connectivityManager.unregisterNetworkCallback(networkCallback) }
    }

    override suspend fun login(email: String, password: String): Result<User> =
        authDataSources.login(email, password)

    override fun logout() {
        authDataSources.logout()
    }

}