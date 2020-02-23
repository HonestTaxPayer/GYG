package gyg.reviews.presentation

import gyg.reviews.domain.model.ReviewsParams
import gyg.reviews.presentation.ReviewsViewModel.Companion.TOUR_ID
import io.mockk.*
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNull
import org.junit.Before
import org.junit.Test

class ViewModelTest {

    private lateinit var viewModel: ReviewsViewModel

    @Before
    fun setUp() {
        viewModel = spyk(ReviewsViewModel(mockk(relaxed = true)))
        every { viewModel.getReviews(any()) } just Runs
    }

    @Test
    fun `test sort date descending`() {
        val slot = slot<ReviewsParams>()

        viewModel.onSortOptionChanged(0)

        verify { viewModel.getReviews(capture(slot)) }

        assertEquals(TOUR_ID, slot.captured.tourId)
        assertNull(slot.captured.sort)
    }

    @Test
    fun `test sort date ascending`() {
        val slot = slot<ReviewsParams>()

        viewModel.onSortOptionChanged(1)

        verify { viewModel.getReviews(capture(slot)) }

        assertEquals(TOUR_ID, slot.captured.tourId)
        assertEquals(
            "${ReviewsViewModel.SORT_DATE}:${ReviewsViewModel.TYPE_ASCENDING}",
            slot.captured.sort
        )
    }

    @Test
    fun `test sort rating descending`() {
        val slot = slot<ReviewsParams>()

        viewModel.onSortOptionChanged(2)

        verify { viewModel.getReviews(capture(slot)) }

        assertEquals(TOUR_ID, slot.captured.tourId)
        assertEquals(
            "${ReviewsViewModel.SORT_RATING}:${ReviewsViewModel.TYPE_DESCENDING}",
            slot.captured.sort
        )
    }

    @Test
    fun `test sort rating ascending`() {
        val slot = slot<ReviewsParams>()

        viewModel.onSortOptionChanged(3)

        verify { viewModel.getReviews(capture(slot)) }

        assertEquals(TOUR_ID, slot.captured.tourId)
        assertEquals(
            "${ReviewsViewModel.SORT_RATING}:${ReviewsViewModel.TYPE_ASCENDING}",
            slot.captured.sort
        )
    }
}