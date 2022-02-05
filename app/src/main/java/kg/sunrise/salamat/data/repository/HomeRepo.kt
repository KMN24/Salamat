package kg.sunrise.salamat.data.repository

import kg.sunrise.salamat.base.repository.BaseRepository
import kg.sunrise.salamat.data.remote.api.HomeApi
import kg.sunrise.salamat.data.remote.response.*
import retrofit2.Response

class HomeRepo(private val api : HomeApi) : BaseRepository() {
    suspend fun getShortCategories(): Response<ArrayList<CategoryShortResponse>>? = getRequest {
        api.getShortCategories()
    }

    suspend fun getFullCategories(slug: String): Response<CategoryFullResponse>? = getRequest {
        api.getFullCategories(slug)
    }
    suspend fun getCategoriesItem(slug: String): Response<CategoryItemResponse>? = getRequest {
        api.getCategoriesItem(slug)
    }
    suspend fun getFeedback(): Response<ArrayList<FreqAskedQuesResponse>>? = getRequest{
            api.getFeedback()
    }
    suspend fun getFeedbackDetail(id: Int): Response<FreqAskedQuesDetailResponse>? = getRequest{
            api.getFeedbackDetail(id)
    }
    suspend fun getTestQuestions(slug: String) : Response<ArrayList<TestQuestionsItem>>? = getRequest {
        api.getTestQuestions(slug)
    }

}