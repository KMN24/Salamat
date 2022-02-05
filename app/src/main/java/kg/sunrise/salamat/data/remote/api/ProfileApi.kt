package kg.sunrise.salamat.data.remote.api

import kg.sunrise.salamat.data.remote.response.AboutApp
import kg.sunrise.salamat.data.remote.response.ChangeRegionResponse

import kg.sunrise.salamat.data.remote.response.HelpResponse

import retrofit2.Response
import retrofit2.http.*

interface ProfileApi {
    @GET("about_app/")
    suspend fun getAboutApp() : Response<AboutApp>
    @GET("help/")
    suspend fun getHelp() : Response<HelpResponse>

    @FormUrlEncoded
    @PUT("change_region/")
    suspend fun changeRegion(@Field("region") region: String): Response<ChangeRegionResponse>
}