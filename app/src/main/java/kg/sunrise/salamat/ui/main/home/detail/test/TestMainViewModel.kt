package kg.sunrise.salamat.ui.main.home.detail.test


import kg.sunrise.salamat.base.BaseViewModel
import kg.sunrise.salamat.data.repository.HomeRepo
import kg.sunrise.salamat.data.state.State



class TestMainViewModel(private val repo: HomeRepo) : BaseViewModel() {
    fun getListTestQuestion(slug: String) = getViewModelScope {
        val response = repo.getTestQuestions(slug)
        if (!response.hasBody()) return@getViewModelScope
        if (response!!.isSuccess())
            _state.value = State.SuccessObjectState(response.body())
    }
}