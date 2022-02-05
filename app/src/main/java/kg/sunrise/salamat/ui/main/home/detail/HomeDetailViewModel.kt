package kg.sunrise.salamat.ui.main.home.detail

import kg.sunrise.salamat.base.BaseViewModel
import kg.sunrise.salamat.data.repository.HomeRepo
import kg.sunrise.salamat.data.state.State
import timber.log.Timber

class HomeDetailViewModel(private val repo: HomeRepo) : BaseViewModel() {
    fun getFullCategories(slug: String) = getViewModelScope {
        val response = repo.getFullCategories(slug)
        if (!response.hasBody()) return@getViewModelScope
        if (response!!.isSuccess()) {
            _state.value = State.SuccessObjectState(response.body())
        }
    }

    fun getCategoriesItem(slug: String) = getViewModelScope {
        val response = repo.getCategoriesItem(slug)
        if (!response.hasBody()) return@getViewModelScope

        if (response!!.isSuccess()) {
            _state.value = State.SuccessObjectState(response.body())

        }
    }
}