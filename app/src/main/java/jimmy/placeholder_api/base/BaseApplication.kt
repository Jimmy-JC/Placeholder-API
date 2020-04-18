package jimmy.placeholder_api.base

import android.app.Application
import jimmy.placeholder_api.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            modules(
                listOf(
                    databaseModule, repositoryModule, viewModelModule, retrofitModule, apiModule
                )
            )
        }
    }
}