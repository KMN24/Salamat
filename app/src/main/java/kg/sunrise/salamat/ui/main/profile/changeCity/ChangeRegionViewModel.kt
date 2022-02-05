package kg.sunrise.salamat.ui.main.profile.changeCity

import kg.sunrise.salamat.base.BaseViewModel
import kg.sunrise.salamat.data.repository.ProfileRepo
import kg.sunrise.salamat.data.state.State

class ChangeRegionViewModel(val repo: ProfileRepo) : BaseViewModel() {
    fun changeRegion(region: String) = getViewModelScope {
        val response = repo.changeRegion(region)

        if (!response.hasBody()) return@getViewModelScope

        if (response!!.isSuccess()) {
            _state.value = State.NoItemState
        }
    }
}