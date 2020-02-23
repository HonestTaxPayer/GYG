package gyg.reviews.domain.usecase

import androidx.paging.DataSource
import gyg.reviews.domain.model.ReviewEntity
import gyg.reviews.domain.model.ReviewsParams
import gyg.reviews.domain.repository.ReviewsRepository
import gyg.reviews.domain.usecase.base.UseCase

class GetReviewsUseCase(private val repository: ReviewsRepository) :
    UseCase<ReviewsParams, DataSource.Factory<Int, ReviewEntity>> {

    override fun execute(params: ReviewsParams): DataSource.Factory<Int, ReviewEntity> {
        return repository.getReviews(params)
    }
}