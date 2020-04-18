package jimmy.placeholder_api.data.network.response

import jimmy.placeholder_api.data.network.response.Status.*

data class Resource<out T>(val status: Status, val data: T?, val throwable: Throwable?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(
                SUCCESS,
                data,
                null
            )
        }

        fun <T> error(throwable: Throwable): Resource<T> {
            return Resource(
                ERROR,
                null,
                throwable
            )
        }

        fun <T> loading(): Resource<T> {
            return Resource(
                LOADING,
                null,
                null
            )
        }

        fun <T> finished(): Resource<T> {
            return Resource(
                FINISHED,
                null,
                null
            )
        }
    }


}
