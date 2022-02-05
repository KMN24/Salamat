package kg.sunrise.salamat.data.remote.response

import com.google.gson.annotations.SerializedName

data class PatientResponse(

	@field:SerializedName("access")
	val access: String,

	@field:SerializedName("patient")
	val patient: Patient,

	@field:SerializedName("access_expires_at")
	val accessExpiresAt: String,
)

data class Patient(

	@field:SerializedName("fmc_name")
	val fmcName: String,

	@field:SerializedName("full_name")
	val fullName: String? = null,

	@field:SerializedName("fmc_address")
	val fmcAddress: String,

	@field:SerializedName("pin")
	val pin: String,

	@field:SerializedName("fmc_code")
	val fmcCode: String,

	@field:SerializedName("gsv_code")
	val gsvCode: String,

	@field:SerializedName("gsv_address")
	val gsvAddress: String,

	@field:SerializedName("status_mhi")
	val statusMhi: Boolean,

	@field:SerializedName("gsv_name")
	val gsvName: String,

	@field:SerializedName("gsv_phone")
	val gsvPhone: String? = null
)
