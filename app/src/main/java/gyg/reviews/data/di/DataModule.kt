package gyg.reviews.data.di

import gyg.reviews.data.data_source.ReviewsDataSourceFactory
import gyg.reviews.data.repository.ReviewsRepositoryImpl
import gyg.reviews.domain.model.ReviewsParams
import gyg.reviews.domain.repository.ReviewsRepository
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dataModule = module {

    single {
        ReviewsDataSourceFactory(get(), Dispatchers.IO, ReviewsParams())
    }

    single<ReviewsRepository> { ReviewsRepositoryImpl(get()) }
}