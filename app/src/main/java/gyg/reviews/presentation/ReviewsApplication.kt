package gyg.reviews.presentation

import android.app.Application
import gyg.reviews.BuildConfig
import gyg.reviews.data.di.dataModule
import gyg.reviews.data.di.networkModule
import gyg.reviews.domain.di.domainModule
import gyg.reviews.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber


class ReviewsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidContext(this@ReviewsApplication)
            modules(listOf(dataModule, networkModule, domainModule, presentationModule))
        }
    }
}