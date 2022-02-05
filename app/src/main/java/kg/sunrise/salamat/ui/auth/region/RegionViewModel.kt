package kg.sunrise.salamat.ui.auth.region

import kg.sunrise.salamat.base.BaseViewModel
import kg.sunrise.salamat.data.repository.AuthRepo
import kg.sunrise.salamat.data.state.State

class RegionViewModel(private val repo : AuthRepo) : BaseViewModel() {

    fun setRegion(username: String, token: String , region : String) = getViewModelScope {
        val response = repo.setRegion(username, token , region)

        if (!response.hasBody()) return@getViewModelScope

        if (response!!.isSuccess()) {
            _state.value = State.SuccessObjectState(response.body())
        }
    }
}