package kg.sunrise.salamat.data.remote.response

import com.google.gson.annotations.SerializedName

data class DoctorDayResponse(
	@SerializedName("datetime")
	val datetime: String,
	@SerializedName("is_available")
	val isAvailable: Boolean,
	@SerializedName("appointment")
	val appointment: Appointment ?= null
)

data class CancellationReason(
	@SerializedName("description")
	val description: String,
	@SerializedName("creation_source")
	val creationSource: String
)

data class Appointment(
	@SerializedName("patient_pin")
	val patientPin: String,
	@SerializedName("appointment_time")
	val appointmentTime: String,
	@SerializedName("appointment_date")
	val appointmentDate: String,
	@SerializedName("created_at")
	val createdAt: String,
	@SerializedName("gsv_address")
	val gsvAddress: String,
	@SerializedName("cancellation_reason")
	val cancellationReason: CancellationReason,
	@SerializedName("gsv_name")
	val gsvName: String,
	@SerializedName("fmc_name")
	val fmcName: String,
	@SerializedName("doctor_speciality")
	val doctorSpeciality: String,
	@SerializedName("patient_name")
	val patientName: String,
	@SerializedName("id")
	val id: Int,
	@SerializedName("floor")
	val floor: Int,
	@SerializedName("doctor_name")
	val doctorName: String,
	@SerializedName("cabinet")
	val cabinet: String,
	@SerializedName("status")
	val status: String,
	@SerializedName("status_mhi")
	val statusMhi: Boolean
)
