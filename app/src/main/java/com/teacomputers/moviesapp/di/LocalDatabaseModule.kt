package com.teacomputers.moviesapp.di

import android.content.Context
import androidx.room.Room
import com.teacomputers.data.dataSource.localDatabase.MyDatabase
import com.teacomputers.data.dataSource.localDatabase.dao.TrendingDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MyDatabase =
        Room.databaseBuilder(
            context,
            MyDatabase::class.java,
            "MyDatabase"
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

    @Provides
    @Singleton
    fun provideMyDao(database: MyDatabase): TrendingDao = database.trendingDao()
}
