package com.example.teaworkshop.di

import com.example.teaworkshop.data.ItemRepository
import com.example.teaworkshop.data.ItemRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepoModule {

    @Binds
    @ViewModelScoped
    abstract fun bindRepository(
        repositoryImpl: ItemRepositoryImpl
    ): ItemRepository
}