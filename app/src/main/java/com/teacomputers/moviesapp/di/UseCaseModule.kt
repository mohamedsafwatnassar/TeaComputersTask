package com.teacomputers.moviesapp.di

import com.teacomputers.domain.repo.MovieRepo
import com.teacomputers.domain.usecases.GetTrendingMovieUseCase
import com.teacomputers.domain.usecases.GetTrendingSeriesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetTrendingMovieUseCase(movieRepo: MovieRepo): GetTrendingMovieUseCase {
        return GetTrendingMovieUseCase(movieRepo)
    }

    @Provides
    fun provideGetTrendingSeriesUseCase(movieRepo: MovieRepo): GetTrendingSeriesUseCase {
        return GetTrendingSeriesUseCase(movieRepo)
    }
}
