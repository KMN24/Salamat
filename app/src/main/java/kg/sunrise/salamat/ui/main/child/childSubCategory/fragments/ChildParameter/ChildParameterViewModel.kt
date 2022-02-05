package kg.sunrise.salamat.ui.main.child.childSubCategory.fragments.ChildParameter

import kg.sunrise.salamat.base.BaseViewModel
import kg.sunrise.salamat.data.remote.response.ChildParametersInfoPost
import kg.sunrise.salamat.data.repository.ChildRepo
import kg.sunrise.salamat.data.state.State

class ChildParameterViewModel(private val repo: ChildRepo) : BaseViewModel() {
    fun postChildDetailInfo(slug: String, childParametersInfoPost: ChildParametersInfoPost) =
        getViewModelScope {
            val response = repo.postChildDetailInfo(slug, childParametersInfoPost)
            if (!response.hasBody())
                return@getViewModelScope
            if (response!!.isSuccessful)
                _state.value = State.NoItemState
        }
}