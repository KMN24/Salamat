package kg.sunrise.salamat.data.repository

import kg.sunrise.salamat.base.repository.BaseRepository
import kg.sunrise.salamat.data.remote.api.AuthApi
import kg.sunrise.salamat.data.remote.response.CheckNumberResponse
import kg.sunrise.salamat.data.remote.response.RegionResponse
import kg.sunrise.salamat.data.remote.response.User
import okhttp3.ResponseBody
import retrofit2.Response

class AuthRepo(private val api: AuthApi) : BaseRepository() {
    suspend fun setRegion(username: String, token: String, region: String): Response<RegionResponse>? =
        getRequest {
            api.setRegion(username, token, region)
        }
    suspend fun setLogin(username: String) : Response<User>? = getRequest {
        api.setLogin(username)
    }

    suspend fun getExistNumber(ordering: String): Response<CheckNumberResponse>? = getRequest {
        api.getExistNumber(ordering)
    }
}