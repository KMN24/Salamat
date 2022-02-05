package kg.sunrise.salamat.data.remote.response

import com.google.gson.annotations.SerializedName

data class ChildParametersInfoPost(
    @SerializedName("created_at")
    val createdAt: String = "",
    @SerializedName("key_kg")
    val keyKg: String = "",
    @SerializedName("key_ru")
    val keyRu: String,
    @SerializedName("updated_at")
    val updatedAt: String = "",
    val value: String
)

