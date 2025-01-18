package com.roxasjearom.spotifybootleg.di

import com.roxasjearom.spotifybootleg.data.repository.MockMainRepositoryImpl
import com.roxasjearom.spotifybootleg.domain.repository.MainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RepositoryModule {

    @ActivityRetainedScoped
    @Binds
    abstract fun bindMainRepository(impl: MockMainRepositoryImpl): MainRepository
}
