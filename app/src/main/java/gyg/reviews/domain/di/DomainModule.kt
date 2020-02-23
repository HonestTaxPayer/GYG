package gyg.reviews.domain.di

import gyg.reviews.domain.usecase.GetReviewsUseCase
import org.koin.dsl.module

val domainModule = module {

    factory { GetReviewsUseCase(get()) }
}