package gyg.reviews.data.model

data class ReviewListResponse(
    val reviews: List<ReviewResponse>,
    val totalCount: Long,
    val averageRating: Double
)