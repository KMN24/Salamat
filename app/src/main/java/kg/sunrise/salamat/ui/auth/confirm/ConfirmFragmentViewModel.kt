package kg.sunrise.salamat.ui.auth.confirm

import kg.sunrise.salamat.base.BaseViewModel
import kg.sunrise.salamat.data.repository.AuthRepo
import kg.sunrise.salamat.data.state.State

class ConfirmFragmentViewModel(private val repo : AuthRepo) : BaseViewModel() {
    fun setLogin(userName : String) = getViewModelScope {
        val response = repo.setLogin(userName)
        if(!response.hasBody())
            return@getViewModelScope
        if(response!!.isSuccess())
            _state.value = State.SuccessObjectState(response.body())

    }

}