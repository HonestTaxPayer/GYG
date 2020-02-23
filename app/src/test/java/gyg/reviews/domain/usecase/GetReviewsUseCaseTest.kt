package gyg.reviews.domain.usecase

import androidx.paging.DataSource
import gyg.reviews.domain.model.ReviewEntity
import gyg.reviews.domain.model.ReviewsParams
import gyg.reviews.domain.repository.ReviewsRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetReviewsUseCaseTest {

    private lateinit var usecase: GetReviewsUseCase
    private val repository: ReviewsRepository = mockk(relaxed = true)

    @Before
    fun setUp() {
        usecase = GetReviewsUseCase(repository)
    }

    @Test
    fun execute() {
        val reviewsParams = ReviewsParams()
        val mockFactory: DataSource.Factory<Int, ReviewEntity> = mockk(relaxed = true)
        every { repository.getReviews(reviewsParams) } returns mockFactory

        assertEquals(mockFactory, usecase.execute(reviewsParams))
        verify { repository.getReviews(reviewsParams) }
    }
}