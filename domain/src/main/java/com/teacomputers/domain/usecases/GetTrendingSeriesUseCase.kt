package com.teacomputers.domain.usecases

import com.teacomputers.domain.repo.MovieRepo

class GetTrendingSeriesUseCase(private val movieRepo: MovieRepo) {
    suspend operator fun invoke() = movieRepo.getSeries()
}
