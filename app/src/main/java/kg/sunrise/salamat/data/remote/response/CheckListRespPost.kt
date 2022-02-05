package kg.sunrise.salamat.data.remote.response

import com.google.gson.annotations.SerializedName

data class CheckListRespPost(
    @SerializedName("checked_date")
    val checkedDate: String,
    @SerializedName("checklist_item")
    val checklistItem: Int,
    val child: String
)