package jimmy.placeholder_api.data.repository

import io.reactivex.Flowable
import jimmy.placeholder_api.data.model.Photo
import jimmy.placeholder_api.data.network.api.ApiService
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

internal class PhotosRepositoryTest {

    private lateinit var photosRepository: PhotosRepository

    @Mock
    private lateinit var apiService: ApiService

    @BeforeEach
    fun init() {
        MockitoAnnotations.initMocks(this)
        photosRepository = PhotosRepository(apiService)
    }

    @Test
    fun getAlbumPhotosSuccess() {
        val albumId = 1
        val photos = ArrayList<Photo>()
        val assumedData = Flowable.just(photos.toList())

        `when`(apiService.getAlbumPhotos(albumId))
            .thenReturn(assumedData)

        photosRepository.getAlbumPhotos(albumId)
            .test()
            .await()
            .assertValue(photos.toList())
    }

    @Test
    fun getAlbumPhotosFailure() {
        val albumId = 2
        val photos = ArrayList<Photo>()
        val assumedData = Flowable.just(photos.toList())

        `when`(apiService.getAlbumPhotos(albumId))
            .thenReturn(null)

        val actualData = photosRepository.getAlbumPhotos(albumId)

        verify(apiService).getAlbumPhotos(albumId)
        verifyNoMoreInteractions(apiService)
        assertNotEquals(assumedData, actualData)
    }
}