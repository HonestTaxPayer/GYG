package gyg.reviews.data.interactor

import android.service.voice.VoiceInteractionSession
import gyg.reviews.data.model.ReviewListResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GetReviewsInteractor {

    @Headers("User-Agent: GetYourGuide")
    @GET("activities/{id}/reviews")
    suspend fun getReviews(
        @Path("id") tourId: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("sort") sort: String?
    ): ReviewListResponse
}