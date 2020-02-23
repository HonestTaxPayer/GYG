package gyg.reviews.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import gyg.reviews.domain.model.ReviewEntity
import gyg.reviews.domain.model.ReviewsParams
import gyg.reviews.domain.usecase.GetReviewsUseCase
import org.koin.core.KoinComponent

class ReviewsViewModel(private val getReviewsUseCase: GetReviewsUseCase) : ViewModel(),
    KoinComponent {

    private lateinit var _reviewsLiveData: LiveData<PagedList<ReviewEntity>>
    val reviewsLiveData: LiveData<PagedList<ReviewEntity>>
        get() = _reviewsLiveData

    private val config = PagedList.Config.Builder()
        .setPageSize(REVIEWS_PER_PAGE)
        .setInitialLoadSizeHint(REVIEWS_INITIAL_LOAD_SIZE)
        .setEnablePlaceholders(false)
        .build()

    init {
        getReviews(ReviewsParams(tourId = TOUR_ID))
    }

    private fun getReviews(params: ReviewsParams) {
        _reviewsLiveData = LivePagedListBuilder(getReviewsUseCase.execute(params), config).build()
    }

    fun onSortOptionChanged(sortOptionPos: Int) {
        val params: ReviewsParams = when (sortOptionPos) {
            1 -> ReviewsParams(tourId = TOUR_ID, sort = "${SORT_DATE}:${TYPE_ASCENDING}")
            2 -> ReviewsParams(tourId = TOUR_ID, sort = "${SORT_RATING}:${TYPE_DESCENDING}")
            3 -> ReviewsParams(tourId = TOUR_ID, sort = "${SORT_RATING}:${TYPE_ASCENDING}")
            else -> ReviewsParams(tourId = TOUR_ID)
        }
        getReviews(params)
    }

    companion object {
        const val REVIEWS_PER_PAGE = 10
        const val REVIEWS_INITIAL_LOAD_SIZE = REVIEWS_PER_PAGE * 2
        const val TOUR_ID = "23776"
        const val SORT_RATING = "rating"
        const val SORT_DATE = "date"
        const val TYPE_ASCENDING = "asc"
        const val TYPE_DESCENDING = "desc"
    }
}