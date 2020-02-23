package gyg.reviews.presentation.adapter

import gyg.reviews.domain.model.ReviewEntity

interface OnReviewClickedListener {

    fun onReviewClicked(reviewEntity: ReviewEntity)
}