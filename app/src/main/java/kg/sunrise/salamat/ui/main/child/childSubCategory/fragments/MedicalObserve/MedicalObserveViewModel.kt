package kg.sunrise.salamat.ui.main.child.childSubCategory.fragments.MedicalObserve


import kg.sunrise.salamat.base.BaseViewModel
import kg.sunrise.salamat.data.remote.response.CheckListResp
import kg.sunrise.salamat.data.repository.ChildRepo
import kg.sunrise.salamat.data.state.State

class MedicalObserveViewModel(private val repo: ChildRepo) : BaseViewModel() {
    fun postCheckListResp(
        checked_date: String,
        checklist_item: Int,
        child: String
    ) = getViewModelScope {
        val response = repo.postCheckListResp(checked_date, checklist_item, child)
        if (!response.hasBody())
            return@getViewModelScope
        if (response!!.isSuccessful)
            _state.value = State.NoItemState
    }


}