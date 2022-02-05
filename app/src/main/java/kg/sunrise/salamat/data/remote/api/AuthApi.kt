package kg.sunrise.salamat.data.remote.api

import kg.sunrise.salamat.data.remote.response.CheckNumberResponse
import kg.sunrise.salamat.data.remote.response.RegionResponse
import kg.sunrise.salamat.data.remote.response.User
import retrofit2.Response
import retrofit2.http.*

interface AuthApi {

    @FormUrlEncoded
    @POST("registration/")
    suspend fun setRegion(
        @Field("username") username: String,
        @Field("token") fireBaseToken : String,
        @Field("region") region: String
    ): Response<RegionResponse>

    @FormUrlEncoded
   @POST("login/")
   suspend fun setLogin(@Field("username") username : String) : Response<User>


    @FormUrlEncoded
    @POST("check_user/")
    suspend fun getExistNumber(
        @Field("username") phone: String
    ): Response<CheckNumberResponse>

}