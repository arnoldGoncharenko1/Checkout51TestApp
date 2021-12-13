package com.example.checkout51testapp.di

import android.content.Context
import com.example.checkout51testapp.data.AppDatabase
import com.example.checkout51testapp.data.OffersDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt/Dagger Module to help with injections through out the app.
 */
@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideOffersDao(appDatabase: AppDatabase): OffersDao {
        return appDatabase.offerDao()
    }
}