package kg.sunrise.salamat.ui.main.child

import kg.sunrise.salamat.base.BaseViewModel
import kg.sunrise.salamat.data.repository.ChildRepo
import kg.sunrise.salamat.data.state.State

class ChildViewModel(private val repo: ChildRepo) : BaseViewModel() {
    fun getChildList() = getViewModelScope {
        val response = repo.getChildList()
        if (!response.hasBody()) return@getViewModelScope

        if (response!!.isSuccess()) {
            _state.value = State.SuccessObjectState(response.body())
        }
    }
}