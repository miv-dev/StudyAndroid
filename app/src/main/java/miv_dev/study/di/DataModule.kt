package miv_dev.study.di

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import miv_dev.study.data.network.AuthDataSources


@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    fun provideAuthDataSources(): AuthDataSources {
        return AuthDataSources(Firebase.auth)
    }
}