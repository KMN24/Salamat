package kg.sunrise.salamat.ui.main.home.doctors_appointment.step1

import kg.sunrise.salamat.base.BaseViewModel
import kg.sunrise.salamat.data.repository.MedBackRepo
import kg.sunrise.salamat.data.state.State

class DoctorsAppointmentStep1ViewModel(val repo: MedBackRepo) : BaseViewModel() {

    fun getPatient(pin: String, phone: String) = getViewModelScope {
        val response = repo.getPatient(pin, phone)
        if (!response.hasBody()) return@getViewModelScope

        if (response!!.isSuccess()) {
            _state.value = State.SuccessObjectState(response.body())
        }
    }

}