package kg.sunrise.salamat.data.remote.response

import com.google.gson.annotations.SerializedName

data class CategoryItemResponse(
    @SerializedName("inline_content")
    val content: InLineResp,
    @SerializedName("check_list")
    val medicalSupport: ArrayList<CheckListResp>,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("title_ru")
    val titleRu: String,
    @SerializedName("title_kg")
    val titleKg: String,
    @SerializedName("title")
    val title: String
) {

    fun convertToDTO(isRusLang: Boolean): CategoryItemDTO {
        return CategoryItemDTO(
            title = if (isRusLang) this.titleRu else this.titleKg,
            slug = this.slug,
            duration = this.duration,
            content = this.content.convertToDTO(isRusLang),
            medicalSupport = ArrayList(this.medicalSupport.map { it.convertToDTO(isRusLang) })
        )
    }
}

data class InLineResp(
    @SerializedName("doctor_supervision_kg")
    val doctorSupervisionKg: String,
    @SerializedName("doctor_supervision_ru")
    val doctorSupervisionRu: String,
    @SerializedName("parent_responsibilities_kg")
    val parentResponsibilitiesKg: String,
    @SerializedName("parent_responsibilities_ru")
    val parentResponsibilitiesRu: String
) {

    fun convertToDTO(isRusLang: Boolean): CategoryContentDTO {
        return CategoryContentDTO(
            doctorSupervision = if (isRusLang) this.doctorSupervisionRu else this.doctorSupervisionKg,
           parentResponsibilities = if (isRusLang) this.parentResponsibilitiesRu else this.parentResponsibilitiesKg
        )
    }
}

data class CheckListResp(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title_ru")
    val title_ru: String,
    @SerializedName("title_kg")
    val title_kg: String,
    @SerializedName("description_ru")
    val description_ru: String,
    @SerializedName("description_kg")
    val description_kg: String,
) {
    fun convertToDTO(isRusLang: Boolean): CheckListDTO {
        return CheckListDTO(
            id = this.id,
            title = if (isRusLang) this.title_ru else this.title_kg,
            description = if (isRusLang) this.description_ru else this.description_kg
        )
    }
}

data class CategoryItemDTO(
    val title: String,
    val slug: String,
    val duration: Int,
    val content: CategoryContentDTO,
    val medicalSupport: ArrayList<CheckListDTO>
)

data class CategoryContentDTO(
    val doctorSupervision: String,
    val parentResponsibilities: String
)

data class CheckListDTO(
    val id: Int,
    val title: String,
    val description: String
)

