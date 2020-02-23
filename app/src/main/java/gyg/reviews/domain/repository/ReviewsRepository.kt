package gyg.reviews.domain.repository

import androidx.paging.DataSource
import gyg.reviews.domain.model.ReviewEntity
import gyg.reviews.domain.model.ReviewsParams

interface ReviewsRepository {

    fun getReviews(reviewsParams: ReviewsParams): DataSource.Factory<Int, ReviewEntity>
}