package gyg.reviews.data.data_source

import androidx.annotation.VisibleForTesting
import androidx.paging.DataSource
import gyg.reviews.data.model.ReviewResponse
import gyg.reviews.domain.model.ReviewsParams
import kotlinx.coroutines.CoroutineScope
import retrofit2.Retrofit
import kotlin.coroutines.CoroutineContext

class ReviewsDataSourceFactory(
    private val retrofit: Retrofit,
    private val coroutineContext: CoroutineContext,
    private var reviewsDataSource: ReviewsDataSource,
    initialReviewParams: ReviewsParams
) :
    DataSource.Factory<Int, ReviewResponse>() {

    @VisibleForTesting
    internal var reviewsParams = initialReviewParams

    override fun create(): DataSource<Int, ReviewResponse> {
        reviewsDataSource =
            ReviewsDataSource(retrofit, reviewsParams, CoroutineScope(coroutineContext))
        return reviewsDataSource
    }

    fun setReviewsParams(reviewsParams: ReviewsParams) {
        this.reviewsParams = reviewsParams
        reviewsDataSource.invalidate()
    }
}