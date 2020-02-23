package gyg.reviews.data.repository

import androidx.paging.DataSource
import gyg.reviews.data.data_source.ReviewsDataSourceFactory
import gyg.reviews.domain.model.ReviewEntity
import gyg.reviews.domain.model.ReviewsParams
import gyg.reviews.domain.repository.ReviewsRepository

class ReviewsRepositoryImpl(private val factory: ReviewsDataSourceFactory) : ReviewsRepository {

    override fun getReviews(reviewsParams: ReviewsParams): DataSource.Factory<Int, ReviewEntity> {
        factory.setReviewsParams(reviewsParams)
        return factory.map { it.map() }
    }
}