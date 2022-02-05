package kg.sunrise.salamat.data.repository

import kg.sunrise.salamat.base.repository.BaseRepository
import kg.sunrise.salamat.data.remote.api.MedBackApi
import kg.sunrise.salamat.data.remote.response.*
import retrofit2.Response

class MedBackRepo(val api: MedBackApi): BaseRepository() {
    suspend fun getPatient(pin: String, phone:String): Response<PatientResponse> ?= getRequest {
        api.getPatient( pin, phone)
    }

    suspend fun getDoctorDaySchedule(date: String, ppi: Int): Response<ArrayList<DoctorDayResponse>> ?= getRequest {
        api.getDoctorDaySchedule(date, ppi)
    }

    suspend fun setAppointment(appointment: AppointmentPost): Response<DoctorAppointmentResponse> ?= getRequest {
        api.setAppointment(appointment)
    }

    suspend fun deleteAppointment(id: Int): Response<DeleteAppointmentResponse> ?= getRequest {
        api.deleteAppointment(id)
    }

    suspend fun getAppointment(id: Int): Response<AppointmentResponse> ?= getRequest {
        api.getAppointment(id)
    }

    suspend fun getDoctors(): Response<ArrayList<DoctorsResponse>> ?= getRequest {
        api.getDoctors()
    }
}
