package kg.sunrise.salamat.ui.main.profile.aboutApp

import kg.sunrise.salamat.base.BaseViewModel
import kg.sunrise.salamat.data.repository.ProfileRepo

import kg.sunrise.salamat.data.state.State
import timber.log.Timber

class AboutAppViewModel(private val profileRepositoy : ProfileRepo) : BaseViewModel() {
    fun getAboutApp() = getViewModelScope {
        val response = profileRepositoy.getAboutApp()
        if(!response.hasBody()) return@getViewModelScope
        if(response!!.isSuccess()){
                _state.value = State.SuccessObjectState(response.body())
            Timber.d(response.body().toString())
        }
    }


}