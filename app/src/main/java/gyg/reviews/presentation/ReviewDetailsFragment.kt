package gyg.reviews.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import gyg.reviews.R
import gyg.reviews.domain.model.ReviewEntity
import kotlinx.android.synthetic.main.fragment_review_details.*

class ReviewDetailsFragment : Fragment(R.layout.fragment_review_details) {

    private val args: ReviewDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setTitle(R.string.tour_details_title)
        val review = args.review

        setAuthorInfo(review)
        setAuthorPhoto(review)
        setMessage(review)
        review_item_date.text = review.date.formatDate()
        review_item_rating.rating = review.rating.toFloat()
    }

    private fun setMessage(review: ReviewEntity) {
        if (review.message.isEmpty()) {
            review_item_divider.visibility = View.GONE
            review_item_text_label.visibility = View.GONE
        } else {
            review_item_text.text = review.message
        }
    }

    private fun setAuthorInfo(review: ReviewEntity) {
        val stringBuilder = StringBuilder()
        stringBuilder.append(review.author.fullName)

        if (review.travelerType != null) {
            stringBuilder.append(" (${review.travelerType})")
        }
        if (review.author.country != null) {
            stringBuilder.append(" - ${review.author.country}")
        }
        review_item_author_info.text = stringBuilder.toString()
    }

    private fun setAuthorPhoto(review: ReviewEntity) {
        if (review.author.photo != null) {
            Glide.with(requireContext())
                .load(review.author.photo)
                .into(review_item_author_photo)
            review_item_author_photo_placeholder.visibility = View.GONE
        } else {
            review_item_author_photo_placeholder.text =
                review.author.fullName.capitalize()[0].toString()
            review_item_author_photo.visibility = View.GONE
        }
    }
}