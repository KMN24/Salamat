package kg.sunrise.salamat.data.remote.response

import com.google.gson.annotations.SerializedName

data class AppointmentResponse(

	@field:SerializedName("patient_pin")
	val patientPin: String,

	@field:SerializedName("appointment_time")
	val appointmentTime: String,

	@field:SerializedName("appointment_date")
	val appointmentDate: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("gsv_address")
	val gsvAddress: String,

	@field:SerializedName("status_mhi")
	val statusMhi: Boolean,

	@field:SerializedName("cancellation_reason")
	val cancellationReason: Any,

	@field:SerializedName("gsv_name")
	val gsvName: String,

	@field:SerializedName("fmc_name")
	val fmcName: String,

	@field:SerializedName("doctor_speciality")
	val doctorSpeciality: String,

	@field:SerializedName("patient_name")
	val patientName: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("floor")
	val floor: Int,

	@field:SerializedName("doctor_name")
	val doctorName: String,

	@field:SerializedName("cabinet")
	val cabinet: String,

	@field:SerializedName("status")
	val status: String
)
