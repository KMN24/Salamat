package kg.sunrise.salamat.base.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import timber.log.Timber

abstract class BaseRepository {
    suspend fun <T>getRequest(function : suspend() -> Response<T>?) : Response<T>? {
        return try {
            withContext(Dispatchers.IO) {
                function()
            }
        }catch (e : Exception){
            Timber.e(e.message)
            null
        }
    }
}