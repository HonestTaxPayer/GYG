package gyg.reviews.data.model

import com.google.gson.annotations.SerializedName
import gyg.reviews.domain.model.ReviewEntity

data class ReviewResponse(
    val id: String,
    val author: AuthorResponse,
    val isAnonymous: Boolean,
    val message: String,
    val rating: Double,
    @SerializedName("created") val date: String,
    val language: String,
    val travelerType: String?
) {
    fun map(): ReviewEntity {
        return ReviewEntity(
            id,
            author.map(),
            isAnonymous,
            message,
            rating,
            date,
            language,
            travelerType
        )
    }
}