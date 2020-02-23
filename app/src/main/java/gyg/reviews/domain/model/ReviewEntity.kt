package gyg.reviews.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReviewEntity(
    val id: String,
    val author: AuthorEntity,
    val isAnonymous: Boolean,
    val message: String,
    val rating: Double,
    val date: String,
    val language: String,
    val travelerType: String?
): Parcelable