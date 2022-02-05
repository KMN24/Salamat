package kg.sunrise.salamat.ui.main.child.childAdd

import kg.sunrise.salamat.base.BaseViewModel
import kg.sunrise.salamat.data.remote.response.ChildPost
import kg.sunrise.salamat.data.repository.ChildRepo
import kg.sunrise.salamat.data.state.State

class ChildAddViewModel(private val repo: ChildRepo) : BaseViewModel() {
    fun addChild(childParameters: ChildPost)= getViewModelScope {
        val response = repo.postChildInfo(
                childParameters.name,
                childParameters.gender,
                childParameters.avatar,
                childParameters.birth_date,
                childParameters.parent)
        if (!response.hasBody()) return@getViewModelScope
        if (response!!.isSuccess())
            _state.value = State.NoItemState

    }

    }
