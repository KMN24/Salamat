package kg.sunrise.salamat.data.repository

import kg.sunrise.salamat.base.repository.BaseRepository
import kg.sunrise.salamat.data.remote.api.HomeApi
import kg.sunrise.salamat.data.remote.response.*
import kg.sunrise.salamat.data.remote.response.ChildDetailResponse
import kg.sunrise.salamat.data.remote.response.ChildPost
import kg.sunrise.salamat.data.remote.response.ChildResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response


class ChildRepo(private val api: HomeApi) : BaseRepository() {
    suspend fun getChildList(): Response<ArrayList<ChildResponse>>? = getRequest {
        api.getChildList()
    }

    suspend fun postChildInfo(
        name: String,
        gender: RequestBody,
        avatar: MultipartBody.Part,
        birth_date: RequestBody,
        parent: Int
    ): Response<ChildPost>? = getRequest {
        api.postChildInfo(
            name, gender, avatar, birth_date, parent
        )
    }

    suspend fun postChildDetailInfo(
        slug: String,
        childParametersInfoPost: ChildParametersInfoPost
    ): Response<ChildParametersInfoPost>? = getRequest {
        api.postChildDetailParameters(slug, childParametersInfoPost)
    }

    suspend fun getChildDetailInfo(slug: String): Response<ChildDetailResponse>? = getRequest {
        api.getChildDetailInfo(slug)
    }

    suspend fun deleteChild(slug: String): Response<ResponseBody>? = getRequest {
        api.deleteChild(slug)
    }

    suspend fun postCheckListResp(
        checked_date: String,
        checklist_item: Int,
        child: String
    ): Response<CheckListRespPost>? = getRequest {
        api.postCheckListArchive(checked_date,checklist_item ,
     child)
    }

    suspend fun getChildParameter(slug: String): Response<ArrayList<ChildParameterResponse>>? = getRequest {
        api.getChildParameter(slug)
    }
}



