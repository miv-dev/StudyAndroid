package miv_dev.study.di

import android.content.Context
import android.net.ConnectivityManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import miv_dev.study.data.network.AuthDataSources


@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    fun provideAuthDataSources(): AuthDataSources {
        return AuthDataSources(Firebase.auth)
    }

    @Provides
    fun provideInternetConnectionChecker(@ApplicationContext context: Context): ConnectivityManager {
        return context.getSystemService(ConnectivityManager::class.java)
    }


}