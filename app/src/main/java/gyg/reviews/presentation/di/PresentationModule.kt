package gyg.reviews.presentation.di

import gyg.reviews.presentation.ReviewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { ReviewsViewModel(get()) }
}