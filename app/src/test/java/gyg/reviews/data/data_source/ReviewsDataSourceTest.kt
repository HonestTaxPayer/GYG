package gyg.reviews.data.data_source

import androidx.paging.PageKeyedDataSource
import gyg.reviews.data.data_source.ReviewsDataSource.Companion.INITIAL_OFFSET
import gyg.reviews.data.interactor.GetReviewsInteractor
import gyg.reviews.data.model.ReviewListResponse
import gyg.reviews.data.model.ReviewResponse
import gyg.reviews.domain.model.ReviewsParams
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit

class ReviewsDataSourceTest {

    private lateinit var reviewsDataSource: ReviewsDataSource
    private val retrofit: Retrofit = mockk(relaxed = true)
    private val interactor: GetReviewsInteractor = mockk(relaxed = true)
    private val tourId = "123"
    private val sortParam = "date:asc"

    @Before
    fun setUp() {
        val reviewsParams = ReviewsParams(tourId, sortParam)
        every { retrofit.create(GetReviewsInteractor::class.java) } returns interactor
        reviewsDataSource =
            ReviewsDataSource(retrofit, reviewsParams, CoroutineScope(Dispatchers.Main))
    }

    @Test
    fun loadInitial() = runBlocking {
        val list = ArrayList<ReviewResponse>()
        val response = ReviewListResponse(list, 1L, 0.0)
        val callback: PageKeyedDataSource.LoadInitialCallback<Int, ReviewResponse> =
            mockk(relaxed = true)
        val params =
            PageKeyedDataSource.LoadInitialParams<Int>(10, true)
        coEvery { interactor.getReviews(tourId, 10, INITIAL_OFFSET, sortParam) } returns response

        reviewsDataSource.loadInitial(params, callback)

        verify { callback.onResult(list, null, INITIAL_OFFSET + 1) }
    }

    @Test
    fun loadAfter() = runBlocking {
        val list = ArrayList<ReviewResponse>()
        val response = ReviewListResponse(list, 1L, 0.0)
        val callback: PageKeyedDataSource.LoadCallback<Int, ReviewResponse> =
            mockk(relaxed = true)
        val params =
            PageKeyedDataSource.LoadParams(2, 10)
        coEvery { interactor.getReviews(tourId, 10, 20, sortParam) } returns response

        reviewsDataSource.loadAfter(params, callback)

        verify { callback.onResult(list, params.key + 1) }
    }
}