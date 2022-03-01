package miv_dev.study.di

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import miv_dev.study.data.local.db.UserDatabase
import miv_dev.study.data.network.AuthDataSources
import miv_dev.study.data.network.FireStoreDataSources
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    fun provideAuthDataSources(): AuthDataSources {
        return AuthDataSources(Firebase.auth)
    }

    @Provides
    fun provideFireStoreDataSources(): FireStoreDataSources {
        return FireStoreDataSources(Firebase.firestore)
    }

    @Provides
    fun provideInternetConnectionChecker(@ApplicationContext context: Context): ConnectivityManager {
        return context.getSystemService(ConnectivityManager::class.java)
    }

    @Singleton
    @Provides
    fun provideUserDatabase(application: Application): UserDatabase {
        return UserDatabase.getInstance(application)
    }

}