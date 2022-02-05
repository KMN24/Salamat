package kg.sunrise.salamat.ui.main.profile.help

import kg.sunrise.salamat.base.BaseViewModel
import kg.sunrise.salamat.data.repository.ProfileRepo
import kg.sunrise.salamat.data.state.State

class HelpViewModel(val repo: ProfileRepo) : BaseViewModel() {
    fun getHelp() = getViewModelScope{
        val response = repo.getHelp()
        if (!response.hasBody()) return@getViewModelScope

        if (response!!.isSuccess()) {
            _state.value = State.SuccessObjectState(response.body())
        }
    }
}
