package kg.sunrise.salamat.ui.main.home.doctors_appointment.step2

import kg.sunrise.salamat.base.BaseViewModel
import kg.sunrise.salamat.data.repository.MedBackRepo
import kg.sunrise.salamat.data.state.State

class DoctorsAppointmentStep2ViewModel(val repo: MedBackRepo) : BaseViewModel() {
    fun getDoctors() = getViewModelScope {
        val response = repo.getDoctors()

        if (!response.hasBody()) return@getViewModelScope

        if (response!!.isSuccess()) {
            _state.value = State.SuccessObjectState(response.body())
        }
    }

}