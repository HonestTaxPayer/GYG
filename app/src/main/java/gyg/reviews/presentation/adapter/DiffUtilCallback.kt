package gyg.reviews.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import gyg.reviews.domain.model.ReviewEntity

class DiffUtilCallback : DiffUtil.ItemCallback<ReviewEntity>() {

    override fun areItemsTheSame(oldItem: ReviewEntity, newItem: ReviewEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ReviewEntity, newItem: ReviewEntity): Boolean {
        return oldItem.message == newItem.message
    }

}