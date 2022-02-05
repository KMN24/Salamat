package kg.sunrise.salamat.ui.main.child.childSubCategory.archive

import kg.sunrise.salamat.base.BaseViewModel
import kg.sunrise.salamat.data.repository.ChildRepo
import kg.sunrise.salamat.data.state.State

class ChildArchiveViewModel(private val repo: ChildRepo) : BaseViewModel() {
    fun getChildDetailInfo(slug : String) = getViewModelScope {
        val response = repo.getChildDetailInfo(slug)

        if(!response.hasBody())
            return@getViewModelScope

        if(response!!.isSuccessful)
            _state.value = State.SuccessObjectState(response.body())
    }

    fun getChildParam(slug: String) = getViewModelScope {
        val response = repo.getChildParameter(slug)
        if (!response.hasBody())
            return@getViewModelScope
        if (response!!.isSuccessful){
            _state.value = State.SuccessObjectState(response.body())
        }
    }
}