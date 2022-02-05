package kg.sunrise.salamat.ui.main.home.freqAskedQues.detail.fragment

import kg.sunrise.salamat.base.BaseViewModel
import kg.sunrise.salamat.data.repository.HomeRepo
import kg.sunrise.salamat.data.state.State

class FaqDetailViewModel(val repo: HomeRepo): BaseViewModel() {
    fun getFeedbackDetail(id : Int) = getViewModelScope {
        val response = repo.getFeedbackDetail(id)

        if (!response.hasBody()) return@getViewModelScope

        if (response!!.isSuccess()) {
            _state.value = State.SuccessObjectState(response.body())
        }
    }
}