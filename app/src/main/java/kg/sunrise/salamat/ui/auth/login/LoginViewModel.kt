package kg.sunrise.salamat.ui.auth.login

import kg.sunrise.salamat.base.BaseViewModel
import kg.sunrise.salamat.data.repository.AuthRepo
import kg.sunrise.salamat.data.state.State

class LoginViewModel(private val repo: AuthRepo) : BaseViewModel() {
    fun getExistNumber(ordering: String) = getViewModelScope {
        val response = repo.getExistNumber(ordering)
        if (!response.hasBody()) return@getViewModelScope

        if (response!!.isSuccess()) {
            _state.value = State.SuccessObjectState(response.body())
        }
    }
    fun setLogin(username : String) = getViewModelScope {
        val response = repo.setLogin(username)
        if(!response.hasBody())
            return@getViewModelScope
      if(response!!.isSuccess())
          _state.value = State.SuccessObjectState(response.body())
    }
}