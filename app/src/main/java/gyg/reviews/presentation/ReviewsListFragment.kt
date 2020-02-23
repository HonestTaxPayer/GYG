package gyg.reviews.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import gyg.reviews.R
import gyg.reviews.domain.model.ReviewEntity
import gyg.reviews.presentation.adapter.OnReviewClickedListener
import gyg.reviews.presentation.adapter.ReviewsListAdapter
import kotlinx.android.synthetic.main.fragment_review_list.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class ReviewsListFragment : Fragment(R.layout.fragment_review_list), OnReviewClickedListener {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(R.string.tour_title)
        val model: ReviewsViewModel by viewModel()
        val reviewsList = view.tour_reviews_list
        val adapter = ReviewsListAdapter(this)
        val layoutManager = LinearLayoutManager(requireContext())
        val dividerItemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)

        reviewsList.layoutManager = layoutManager
        reviewsList.addItemDecoration(dividerItemDecoration)
        reviewsList.adapter = adapter
        model.reviewsLiveData.observe(this, Observer<PagedList<ReviewEntity>> { reviews ->
            adapter.submitList(reviews)
        })
    }

    override fun onReviewClicked(reviewEntity: ReviewEntity) {
        val action = ReviewsListFragmentDirections.actionReviewDetails(reviewEntity)
        findNavController().navigate(action)
    }
}