package gyg.reviews.presentation

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
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

    private val viewModel: ReviewsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(R.string.tour_title)
        val reviewsList = view.tour_reviews_list
        val sortSpinner = view.tour_reviews_sort_spinner
        val adapter = ReviewsListAdapter(this)
        val layoutManager = LinearLayoutManager(requireContext())
        val dividerItemDecoration =
            DividerItemDecoration(requireContext(), layoutManager.orientation)

        reviewsList.layoutManager = layoutManager
        reviewsList.addItemDecoration(dividerItemDecoration)
        reviewsList.adapter = adapter
        viewModel.reviewsLiveData.observe(this, Observer<PagedList<ReviewEntity>> { reviews ->
            adapter.submitList(reviews)
        })

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.sort_options,
            R.layout.sort_option
        ).also { sortAdapter ->
            sortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            sortSpinner.adapter = sortAdapter
        }
        sortSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                view?.let {
                    viewModel.onSortOptionChanged(pos)
                }
            }
        }
    }

    override fun onReviewClicked(reviewEntity: ReviewEntity) {
        val action = ReviewsListFragmentDirections.actionReviewDetails(reviewEntity)
        findNavController().navigate(action)
    }
}