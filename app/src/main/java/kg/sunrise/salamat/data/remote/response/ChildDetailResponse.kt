package kg.sunrise.salamat.data.remote.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ChildDetailResponse(
    @SerializedName("all_check_list_items_with_notifications")
    val subData: ArrayList<AllCheckItemsWithNotifications>,
    val archives: ArrayList<Archive>,
    val avatar: String,
    @SerializedName("current_content")
    val currentContent: CurrentContent,
    @SerializedName("formatted_birth_date")
    val formattedBirthDate: String,
    var gender: String,
    val name: String,
    @SerializedName("next_vaccination_date")
    val nextVaccinationDate: String,
    @SerializedName("prev_vaccination_date")
    val prevVaccinationDate: String,
    val slug: String,
    val subcategory: Subcategory
) : Serializable

data class CurrentContent(
    @SerializedName("check_list")
    val checkList: ArrayList<Check>,
    @SerializedName("child_parameter_templates")
    val childParameterTemplates: ArrayList<Any>,
    @SerializedName("inline_content")
    val inlineContent: InlineContent

) : Serializable

data class InlineContent(
    @SerializedName("doctor_supervision_kg")
    val doctorSupervisionKg: String,
    @SerializedName("doctor_supervision_ru")
    val doctorSupervisionRu: String,
    @SerializedName("parent_responsibilities_kg")
    val parentResponsibilitiesKg: String,
    @SerializedName("parent_responsibilities_ru")
    val parentResponsibilitiesRu: String,
) {
    fun convertDTO(isRusLang: Boolean): InlineContentDTO {
        return InlineContentDTO(
            doctorSupervision = if (isRusLang) this.doctorSupervisionRu else this.doctorSupervisionKg,
            parentResponsibilities = if (isRusLang) this.parentResponsibilitiesRu else parentResponsibilitiesKg
        )
    }
}


data class InlineContentDTO(
    val doctorSupervision: String,
    val parentResponsibilities: String
)


data class AllCheckItemsWithNotifications(
    @SerializedName("description_kg")
    val descriptionKg: String,
    @SerializedName("description_ru")
    val descriptionRu: String,
    val id: Int,
    @SerializedName("notification_date")
    val notificationDate: String,
    @SerializedName("subcategory_slug")
    val subcategorySlug: String,
    @SerializedName("title_kg")
    val titleKg: String,
    @SerializedName("title_ru")
    val titleRu: String
) : Serializable {
    fun convertDTO(isRusLang: Boolean): AllCheckItemsDTO {
        return AllCheckItemsDTO(
            description = if (isRusLang) this.descriptionRu else descriptionKg,
            title = if (isRusLang) titleRu else titleKg)
    }

}


data class AllCheckItemsDTO(
    val description: String,
    val title: String
)

data class Archive(
    @SerializedName("checked_date")
    val checkedDate: String,
    @SerializedName("checklist_item")
    val checklistItem: ChecklistItem
) : Serializable
{
    fun convertToDto(isRusLang: Boolean): ArchiveDto{
        return ArchiveDto(
            checkedDate = this.checkedDate,
            checkListItemDto = this.checklistItem.convertToDto(isRusLang)
        )
    }
}

data class ArchiveDto(
    val checkedDate: String,
    val checkListItemDto: CheckListItemDto
)

data class ChecklistItem(
    @SerializedName("description_kg")
    val descriptionKg: String,
    @SerializedName("description_ru")
    val descriptionRu: String,
    val id: Int,
    @SerializedName("title_kg")
    val titleKg: String,
    @SerializedName("title_ru")
    val titleRu: String
){
    fun convertToDto(isRusLang: Boolean): CheckListItemDto{
        return CheckListItemDto(
            description = if (isRusLang) this.descriptionRu else descriptionKg,
            id = this.id,
            title = if (isRusLang) this.titleRu else this.titleKg
        )
    }
}

data class CheckListItemDto(
    val description: String,
    val id: Int,
    val title: String
)


data class Subcategory(
    val duration: String,
    val slug: String,
    @SerializedName("title_kg")
    val titleKg: String,
    @SerializedName("title_ru")
    val titleRu: String
) : Serializable {
    fun convertDTO(isRusLang: Boolean) : SubCategoryDTO {
        return SubCategoryDTO(title = if(isRusLang) titleRu else titleKg)
    }
}

data class SubCategoryDTO(val title : String)

data class Check(
    @SerializedName("description_kg")
    val descriptionKg: String,
    @SerializedName("description_ru")
    val descriptionRu: String,
    val id: Int,
    @SerializedName("title_kg")
    val titleKg: String,
    @SerializedName("title_ru")
    val titleRu: String
) : Serializable {
    fun convertDTO(isRusLang: Boolean) : CheckDTO {
        return CheckDTO(description = if(isRusLang) descriptionRu else descriptionKg ,
        title = if(isRusLang) titleRu else titleKg)
    }
}


data class CheckDTO(val description: String , val title: String)