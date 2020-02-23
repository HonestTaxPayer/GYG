package gyg.reviews.data.data_source

import androidx.paging.PageKeyedDataSource
import gyg.reviews.data.interactor.GetReviewsInteractor
import gyg.reviews.data.model.ReviewResponse
import gyg.reviews.domain.model.ReviewsParams
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import kotlin.coroutines.CoroutineContext

class ReviewsDataSource(
    private val retrofit: Retrofit,
    private val reviewsParams: ReviewsParams,
    coroutineContext: CoroutineContext
) :
    PageKeyedDataSource<Int, ReviewResponse>() {

    private val coroutineScope = CoroutineScope(coroutineContext)

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ReviewResponse>
    ) {
        coroutineScope.launch(Dispatchers.IO) {
            val reviewsResponse = retrofit.create(GetReviewsInteractor::class.java)
                .getReviews(
                    reviewsParams.tourId,
                    params.requestedLoadSize,
                    INITIAL_OFFSET,
                    reviewsParams.sort
                )
            callback.onResult(reviewsResponse.reviews, null, INITIAL_OFFSET + 1)
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, ReviewResponse>
    ) {
        coroutineScope.launch(Dispatchers.IO) {
            val reviewsResponse = retrofit.create(GetReviewsInteractor::class.java)
                .getReviews(
                    reviewsParams.tourId,
                    params.requestedLoadSize,
                    params.key * params.requestedLoadSize,
                    reviewsParams.sort
                )
            callback.onResult(reviewsResponse.reviews, params.key + 1)
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, ReviewResponse>
    ) {
    }

    override fun invalidate() {
        super.invalidate()
        coroutineScope.cancel()
    }

    companion object {
        const val INITIAL_OFFSET = 0
    }
}