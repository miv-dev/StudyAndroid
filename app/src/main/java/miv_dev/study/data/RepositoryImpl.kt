package miv_dev.study.data

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import miv_dev.study.data.network.FireStoreDataSources
import miv_dev.study.domain.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val connectivityManager: ConnectivityManager,
    private val fireStoreDataSources: FireStoreDataSources,
) : Repository {

    private val networkRequest: NetworkRequest =
        NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR).build()

    @OptIn(ExperimentalCoroutinesApi::class)
    override val networkConnection: Flow<Boolean> = channelFlow {
        send(connectivityManager.activeNetworkInfo?.isConnected ?: false)

        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                Log.d(TAG, "onAvailable: Internet is available")
                trySend(true)
            }


            override fun onLost(network: Network) {
                super.onLost(network)
                Log.e(TAG, "onLost: Connection Lost")
                trySend(false)
            }

            override fun onUnavailable() {
                super.onUnavailable()
                Log.e(TAG, "onLost: Connection Lost")
                trySend(false)
            }

        }

        connectivityManager.requestNetwork(
            networkRequest, networkCallback
        )
        awaitClose { connectivityManager.unregisterNetworkCallback(networkCallback) }
    }


    companion object{
        const val TAG = "RepositoryImpl"
    }

}