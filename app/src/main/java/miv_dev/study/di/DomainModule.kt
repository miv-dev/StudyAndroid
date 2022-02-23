package miv_dev.study.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import miv_dev.study.data.RepositoryImpl
import miv_dev.study.domain.Repository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

//    @Singleton
    @Provides
    fun provideRepository(impl: RepositoryImpl): Repository = impl
}