package kg.sunrise.salamat.ui.main.home.freqAskedQues

import android.util.Log
import kg.sunrise.salamat.base.BaseViewModel
import kg.sunrise.salamat.data.repository.HomeRepo
import kg.sunrise.salamat.data.state.State

class FreqAskedQuesViewModel(val repo: HomeRepo) : BaseViewModel() {

    fun getFeedback() = getViewModelScope {
        val response = repo.getFeedback()

        if (!response.hasBody()) return@getViewModelScope

        if (response!!.isSuccess()) {
            _state.value = State.SuccessObjectState(response.body())
        }
    }

}