package kg.sunrise.salamat.ui.main.child.detail

import kg.sunrise.salamat.base.BaseViewModel
import kg.sunrise.salamat.data.repository.ChildRepo
import kg.sunrise.salamat.data.state.State
import timber.log.Timber

class ChildDetailViewModel(private val repo: ChildRepo) : BaseViewModel() {
    fun getChildDetailInfo(slug : String) = getViewModelScope {
        val response = repo.getChildDetailInfo(slug)
        if(!response.hasBody())
            return@getViewModelScope
        if(response!!.isSuccessful)
            _state.value = State.SuccessObjectState(response.body())
    }
    fun deleteChild(slug : String) = getViewModelScope {
        val response = repo.deleteChild(slug)
        if(!response.hasBody())
            return@getViewModelScope
        if(response!!.isSuccessful)
            _state.value = State.NoItemState
    }
}