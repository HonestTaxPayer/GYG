package gyg.reviews.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import gyg.reviews.R
import gyg.reviews.domain.model.ReviewEntity
import gyg.reviews.presentation.formatDate
import kotlinx.android.synthetic.main.review_list_item.view.*


class ReviewsListAdapter(private val clickListener: OnReviewClickedListener) :
    PagedListAdapter<ReviewEntity, ReviewsListAdapter.ReviewViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.review_list_item, parent, false)
        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dateText = itemView.review_item_date
        private val ratingBar = itemView.review_item_rating
        private val previewText = itemView.review_item_text

        fun bind(reviewEntity: ReviewEntity) {
            itemView.setOnClickListener {
                clickListener.onReviewClicked(reviewEntity)
            }
            dateText.text = reviewEntity.date.formatDate()
            if (reviewEntity.message.isNotEmpty()) {
                previewText.text = reviewEntity.message
                previewText.visibility = View.VISIBLE
            } else {
                previewText.visibility = View.GONE
            }
            ratingBar.rating = reviewEntity.rating.toFloat()
        }
    }
}