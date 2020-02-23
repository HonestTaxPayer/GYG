package gyg.reviews.data.model

import gyg.reviews.domain.model.AuthorEntity

data class AuthorResponse(
    val fullName: String,
    val photo: String?,
    val country: String?
) {
    fun map(): AuthorEntity {
        return AuthorEntity(
            fullName,
            photo,
            country
        )
    }
}