package jimmy.placeholder_api.ui.photos

import androidx.lifecycle.Observer
import io.reactivex.Flowable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import jimmy.placeholder_api.data.model.Photo
import jimmy.placeholder_api.data.network.response.Resource
import jimmy.placeholder_api.data.repository.PhotosRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@ExtendWith(InstantExecutorExtension::class)
internal class PhotosViewModelTest {

    private lateinit var photoViewModel: PhotosViewModel

    @Mock
    private lateinit var photoRepository: PhotosRepository

    @Mock
    private lateinit var observer: Observer<Resource<List<Photo>>>

    @BeforeEach
    fun init() {
        MockitoAnnotations.initMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        photoViewModel = PhotosViewModel(photoRepository)
        photoViewModel.listLiveData.observeForever(observer)
    }

    @Test
    fun testApiFetchDataNull() {
        `when`(photoRepository.getAlbumPhotos(1)).thenReturn(null)
        assertNotNull(photoViewModel.listLiveData)
        assertTrue(photoViewModel.listLiveData.hasObservers())
    }

    @Test
    fun testApiFetchDataSuccess() {
        val albumId = 1
        val photos = ArrayList<Photo>()
        val assumedData = Flowable.just(photos.toList())

        `when`(photoRepository.getAlbumPhotos(albumId))
            .thenReturn(assumedData)
        photoViewModel.getDataList(albumId)
        Thread.sleep(10)

        verify(observer).onChanged(Resource.loading())
        verify(observer).onChanged(Resource.finished())
        verify(observer).onChanged(Resource.success(photos))
        verifyNoMoreInteractions(observer)
        assertEquals(Resource.success(photos), photoViewModel.listLiveData.value)
    }

    @Test
    fun testApiFetchDataError() {
        val albumId = 2
        val throwable = Throwable()
        val error = Flowable.error<List<Photo>>(throwable)

        `when`(photoRepository.getAlbumPhotos(albumId))
            .thenReturn(error)
        photoViewModel.getDataList(albumId)
        Thread.sleep(10)

        verify(observer).onChanged(Resource.loading())
        verify(observer).onChanged(Resource.finished())
        verify(observer).onChanged(Resource.error(throwable))
        verifyNoMoreInteractions(observer)
        assertEquals(Resource.error<List<Photo>>(throwable), photoViewModel.listLiveData.value)
    }
}