package kg.sunrise.salamat.data.remote.response

import com.google.gson.annotations.SerializedName

data class ChildParameterResponse(

	@SerializedName("key_kg")
	val keyKg: String,

	@SerializedName("updated_at")
	val updatedAt: String,

	@SerializedName("created_at")
	val createdAt: String,

	@SerializedName("key_ru")
	val keyRu: String,

	@SerializedName("value")
	val value: String
){
	fun convertToDto(isRusLang: Boolean): ChildParameterDTO{
		return ChildParameterDTO(key = if (isRusLang) this.keyRu else this.keyKg ,
			createdAt = this.createdAt,
			updatedAt = this.updatedAt,
			value = this.value
			)
	}
}

data class ChildParameterDTO(
	val key : String,
	val createdAt: String,
	val updatedAt: String,
	val value: String
)

