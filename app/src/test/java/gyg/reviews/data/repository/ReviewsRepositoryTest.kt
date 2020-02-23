package gyg.reviews.data.repository

import gyg.reviews.data.data_source.ReviewsDataSourceFactory
import gyg.reviews.domain.model.ReviewsParams
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class ReviewsRepositoryTest {

    private lateinit var repository: ReviewsRepositoryImpl
    private val factory: ReviewsDataSourceFactory = mockk(relaxed = true)

    @Before
    fun setUp() {
        repository = ReviewsRepositoryImpl(factory)
    }

    @Test
    fun getReviews() {
        val reviewsParams = ReviewsParams()

        repository.getReviews(reviewsParams)

        verify { factory.setReviewsParams(reviewsParams) }
    }
}