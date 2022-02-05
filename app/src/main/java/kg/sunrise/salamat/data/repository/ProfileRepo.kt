package kg.sunrise.salamat.data.repository


import kg.sunrise.salamat.base.repository.BaseRepository
import kg.sunrise.salamat.data.remote.api.ProfileApi
import kg.sunrise.salamat.data.remote.response.AboutApp
import kg.sunrise.salamat.data.remote.response.ChangeRegionResponse
import kg.sunrise.salamat.data.remote.response.HelpResponse
import retrofit2.Response

class ProfileRepo(private val api: ProfileApi) : BaseRepository() {
    suspend fun getAboutApp(): Response<AboutApp>? = getRequest {
        api.getAboutApp()
    }

    suspend fun getHelp(): Response<HelpResponse>? = getRequest {
        api.getHelp()
    }

    suspend fun changeRegion(region: String): Response<ChangeRegionResponse>? = getRequest {
        api.changeRegion(region)
    }

}
