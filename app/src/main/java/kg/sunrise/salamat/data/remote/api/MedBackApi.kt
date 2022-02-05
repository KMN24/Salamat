package kg.sunrise.salamat.data.remote.api

import kg.sunrise.salamat.data.remote.response.*
import retrofit2.Response
import retrofit2.http.*

interface MedBackApi {

    @FormUrlEncoded
    @POST("auth/patient_check")
    suspend fun getPatient(@Field("pin") pin: String, @Field("phone") phone: String) : Response<PatientResponse>

    @GET("day_schedule")
    suspend fun getDoctorDaySchedule(@Query("date") date: String, @Query("ppi") ppi: Int): Response<ArrayList<DoctorDayResponse>>

    @POST("appointments")
    suspend fun setAppointment(@Body appointment : AppointmentPost) : Response<DoctorAppointmentResponse>

    @GET("doctors_list/")
    suspend fun getDoctors() : Response<ArrayList<DoctorsResponse>>

    @DELETE("appointments/{appointment_id}")
    suspend fun deleteAppointment(@Path("appointment_id") appointment_id : Int): Response<DeleteAppointmentResponse>

    @GET("appointments/{appointment_id}")
    suspend fun getAppointment(@Path("appointment_id") appointment_id: Int) : Response<AppointmentResponse>
}