package gyg.reviews.data.data_source

import androidx.paging.DataSource
import gyg.reviews.data.model.ReviewResponse
import gyg.reviews.domain.model.ReviewsParams
import retrofit2.Retrofit
import kotlin.coroutines.CoroutineContext

class ReviewsDataSourceFactory(
    private val retrofit: Retrofit,
    private val coroutineContext: CoroutineContext,
    initialReviewParams: ReviewsParams
) :
    DataSource.Factory<Int, ReviewResponse>() {

    private var reviewsParams = initialReviewParams

    override fun create(): DataSource<Int, ReviewResponse> {
        return ReviewsDataSource(retrofit, reviewsParams, coroutineContext)
    }

    fun setReviewsParams(reviewsParams: ReviewsParams) {
        this.reviewsParams = reviewsParams
    }
}