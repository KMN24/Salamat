package kg.sunrise.salamat.ui.main.profile

import kg.sunrise.salamat.base.BaseViewModel
import kg.sunrise.salamat.data.repository.HomeRepo
import kg.sunrise.salamat.data.state.State

class ProfileViewModel(private val repo: HomeRepo) : BaseViewModel() {
    fun getShortCategories() = getViewModelScope {
        val response = repo.getShortCategories()
        if (!response.hasBody()) return@getViewModelScope

        if (response!!.isSuccess()) {
            _state.value = State.SuccessObjectState(response.body())
        }
    }
}