package kg.sunrise.salamat.data.remote.response

import com.google.gson.annotations.SerializedName

data class DoctorsResponse(

	@SerializedName("doctor_schedules")
	val doctorSchedules: List<DoctorSchedulesItem>,

	@SerializedName("speciality")
	val speciality: String,

	@SerializedName("full_name")
	val fullName: String,

	@SerializedName("ppi")
	val ppi: Int,

	@SerializedName("is_narrow_speciality")
	val isNarrowSpeciality: Boolean,

	@SerializedName("institution_name")
	val institutionName: String
)

data class DoctorSchedulesItem(

	@SerializedName("start_time")
	val startTime: String,

	@SerializedName("system_name")
	val systemName: String,

	@SerializedName("end_time")
	val endTime: String,

	@SerializedName("day_of_week_string")
	val dayOfWeekString: String,

	@SerializedName("day_of_week")
	val dayOfWeek: Int
)
