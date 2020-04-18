package jimmy.placeholder_api.di

import android.app.Application
import androidx.room.Room
import jimmy.placeholder_api.BuildConfig
import jimmy.placeholder_api.data.network.api.ApiService
import jimmy.placeholder_api.data.persistence.AppDatabase
import jimmy.placeholder_api.data.persistence.PhotoDao
import jimmy.placeholder_api.data.repository.AlbumsRepository
import jimmy.placeholder_api.data.repository.CommentsRepository
import jimmy.placeholder_api.data.repository.PhotosRepository
import jimmy.placeholder_api.data.repository.PostsRepository
import jimmy.placeholder_api.ui.albums.AlbumsViewModel
import jimmy.placeholder_api.ui.comments.CommentsViewModel
import jimmy.placeholder_api.ui.favourites.FavouritesViewModel
import jimmy.placeholder_api.ui.photos.PhotosViewModel
import jimmy.placeholder_api.ui.posts.PostsViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel { PostsViewModel(get()) }
    viewModel { CommentsViewModel(get()) }
    viewModel { AlbumsViewModel(get()) }
    viewModel { PhotosViewModel(get()) }
    viewModel { FavouritesViewModel(get()) }
}

val repositoryModule = module {
    single { PostsRepository(get()) }
    single { AlbumsRepository(get()) }
    single { CommentsRepository(get()) }
    single { PhotosRepository(get(), get()) }
}

val apiModule = module {
    fun provideApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
    single { provideApi(get()) }
}

val databaseModule = module {
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "photo.db")
            .allowMainThreadQueries()
            .build()
    }

    fun provideDao(database: AppDatabase): PhotoDao {
        return database.photoDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideDao(get()) }
}

val retrofitModule = module {

    fun provideHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }

    single { provideHttpClient() }
    single { provideRetrofit(get()) }
}
