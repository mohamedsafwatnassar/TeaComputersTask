package com.teacomputers.moviesapp.di

import com.teacomputers.data.dataSource.remote.ApiService
import com.teacomputers.data.dataSource.localDatabase.dao.TrendingDao
import com.teacomputers.data.repo.MovieRepoImpl
import com.teacomputers.domain.repo.MovieRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun provideRepo(apiService: ApiService, trendingDao: TrendingDao): MovieRepo {
        return MovieRepoImpl(apiService, trendingDao)
    }
}
