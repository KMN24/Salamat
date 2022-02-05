package kg.sunrise.salamat.data.remote.api

import kg.sunrise.salamat.data.remote.response.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface HomeApi {

    @GET("category/")
    suspend fun getShortCategories(): Response<ArrayList<CategoryShortResponse>>

    @GET("category/{slug}/")
    suspend fun getFullCategories(
        @Path("slug") slug: String
    ): Response<CategoryFullResponse>

    @GET("subcategory/{slug}/")
    suspend fun getCategoriesItem(
        @Path("slug") slug: String
    ): Response<CategoryItemResponse>

    @GET("my_children/")
    suspend fun getChildList(): Response<ArrayList<ChildResponse>>


    @GET("category/{slug}/test/")
    suspend fun getTestQuestions(@Path("slug") slug: String): Response<ArrayList<TestQuestionsItem>>

    @GET("feedback/")
    suspend fun getFeedback(): Response<ArrayList<FreqAskedQuesResponse>>

    @GET("feedback/{id}/")
    suspend fun getFeedbackDetail(@Path("id") id: Int): Response<FreqAskedQuesDetailResponse>

    @Multipart
    @POST("add_child/")
    suspend fun postChildInfo(
        @Part("name") name: String,
        @Part("gender") gender: RequestBody,
        @Part avatar: MultipartBody.Part,
        @Part("birth_date") birth_date: RequestBody,
        @Part("parent") parent: Int
    ): Response<ChildPost>


    @GET("my_children/{slug}/")
    suspend fun getChildDetailInfo(@Path("slug") slug: String): Response<ChildDetailResponse>

    @DELETE("my_children/{slug}/delete/")
    suspend fun deleteChild(@Path("slug") slug: String): Response<ResponseBody>

    @POST("my_children/{slug}/child_parameter/")
    suspend fun postChildDetailParameters(
        @Path("slug") slug: String,
        @Body childParametersInfoPost: ChildParametersInfoPost
    ): Response<ChildParametersInfoPost>

    @FormUrlEncoded
    @POST("check_list_check/")
    suspend fun postCheckListArchive(
        @Field("checked_date") checked_date: String,
        @Field("checklist_item") checklist_item: Int,
        @Field("child") child : String
    ): Response<CheckListRespPost>

    @GET("my_children/{slug}/child_parameter/")
    suspend fun getChildParameter(@Path("slug") slug: String): Response<ArrayList<ChildParameterResponse>>
}