package kg.sunrise.salamat.ui.main.home.doctors_appointment.step3

import kg.sunrise.salamat.base.BaseViewModel
import kg.sunrise.salamat.data.remote.response.AppointmentPost
import kg.sunrise.salamat.data.repository.MedBackRepo
import kg.sunrise.salamat.data.state.State


class DoctorsAppointmentStep3ViewModel(val repo: MedBackRepo): BaseViewModel() {

    fun getDoctorSchedule(date: String, ppi: Int) = getViewModelScope {

        val response = repo.getDoctorDaySchedule(date, ppi)

        if (!response.hasBody()) return@getViewModelScope

        if (response!!.isSuccess()) {
            _state.value = State.SuccessObjectState(response.body())
        }
    }

    fun setAppointment(appointment: AppointmentPost) = getViewModelScope {

        val response = repo.setAppointment(appointment)

        if(!response.hasBody()) return@getViewModelScope

        if(response!!.isSuccess()){
            _state.value = State.SuccessObjectState(response.body())
        }
    }

    fun deleteAppointment(id:Int) = getViewModelScope {

        val response = repo.deleteAppointment(id)

        if(!response.hasBody()) return@getViewModelScope

        if(response!!.isSuccess()){
            _state.value = State.NoItemState
        }
    }

    fun getAppointment(id : Int) = getViewModelScope {
        val response = repo.getAppointment(id)

        if(!response.hasBody()) return@getViewModelScope

        if(response!!.isSuccess()){
            _state.value = State.SuccessObjectState(response.body())
        }
    }

}