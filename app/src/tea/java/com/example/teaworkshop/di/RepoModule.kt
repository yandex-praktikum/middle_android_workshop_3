package com.example.teaworkshop.di

import com.example.teaworkshop.data.ItemRepository
import com.example.teaworkshop.data.ItemRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
abstract class RepoModule {

    @Binds
    @ActivityScoped
    abstract fun bindRepository(
        repositoryImpl: ItemRepositoryImpl
    ): ItemRepository
}