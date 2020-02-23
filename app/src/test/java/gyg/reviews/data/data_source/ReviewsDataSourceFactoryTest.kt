package gyg.reviews.data.data_source

import gyg.reviews.domain.model.ReviewsParams
import io.mockk.mockk
import io.mockk.verify
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import kotlin.coroutines.CoroutineContext

class ReviewsDataSourceFactoryTest {

    private lateinit var factory: ReviewsDataSourceFactory
    private val retrofit: Retrofit = mockk(relaxed = true)
    private val dataSource: ReviewsDataSource = mockk(relaxed = true)
    private val coroutineContext: CoroutineContext = mockk(relaxed = true)

    @Before
    fun setUp() {
        factory = ReviewsDataSourceFactory(retrofit, coroutineContext, dataSource, ReviewsParams())
    }

    @Test
    fun setReviewParams() {
        val reviewsParams = ReviewsParams()
        factory.setReviewsParams(reviewsParams)

        assertEquals(reviewsParams, factory.reviewsParams)
        verify { dataSource.invalidate() }
    }
}