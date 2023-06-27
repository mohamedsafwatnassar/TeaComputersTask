package com.teacomputers.domain.usecases

import com.teacomputers.domain.repo.MovieRepo

class GetTrendingMovieUseCase(private val movieRepo: MovieRepo) {
    suspend operator fun invoke() = movieRepo.getTrendingMovie()
}
