package gyg.reviews.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AuthorEntity(
    val fullName: String,
    val photo: String?,
    val country: String?
): Parcelable