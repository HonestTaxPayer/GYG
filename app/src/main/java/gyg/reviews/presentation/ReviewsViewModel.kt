package gyg.reviews.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import gyg.reviews.domain.model.ReviewEntity
import gyg.reviews.domain.model.ReviewsParams
import gyg.reviews.domain.usecase.GetReviewsUseCase
import org.koin.core.KoinComponent

class ReviewsViewModel(private val getReviewsUseCase: GetReviewsUseCase) : ViewModel(), KoinComponent {

    private val _reviewsLiveData: LiveData<PagedList<ReviewEntity>>
    val reviewsLiveData: LiveData<PagedList<ReviewEntity>>
        get() = _reviewsLiveData

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(REVIEWS_PER_PAGE)
            .setInitialLoadSizeHint(REVIEWS_INITIAL_LOAD_SIZE)
            .setEnablePlaceholders(false)
            .build()
        _reviewsLiveData = initializedPagedListBuilder(config).build()
    }

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<Int, ReviewEntity> {

        val params = ReviewsParams(tourId = TOUR_ID)

        return LivePagedListBuilder(getReviewsUseCase.execute(params), config)
    }

    companion object {
        const val REVIEWS_PER_PAGE = 10
        const val REVIEWS_INITIAL_LOAD_SIZE = REVIEWS_PER_PAGE * 2
        const val TOUR_ID = "23776"
    }
}