package com.gregkluska.shoppingcenter.di

import com.gregkluska.shoppingcenter.models.Store
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideStore() : Store {
        return Store(1, "First Store", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla volutpat dictum ligula, consectetur lobortis ligula pulvinar non.", "https://icon2.cleanpng.com/20171220/gze/twitter-logo-png-5a3a1851372e76.0876249315137567532269680.jpg", "https://picsum.photos/200/300/?blur")
    }

}