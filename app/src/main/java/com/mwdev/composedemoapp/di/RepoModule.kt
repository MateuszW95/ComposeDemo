package com.mwdev.composedemoapp.di

import com.mwdev.composedemoapp.network.person.IMockApiService
import com.mwdev.composedemoapp.repo.PersonRemoteRepo
import com.mwdev.composedemoapp.repo.PersonRemoteRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Singleton
    @Provides
    fun providesPersonRemoteRepository(api: IMockApiService): PersonRemoteRepo =
        PersonRemoteRepoImpl(apiService = api)

}