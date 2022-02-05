package kg.sunrise.salamat.data.remote.response

import com.google.gson.annotations.SerializedName


data class CategoryFullResponse(
    val subcategories: ArrayList<SubCarResp>,
    val slug: String,
    @SerializedName("title_ru")
    val titleRu: String,
    @SerializedName("title_kg")
    val titleKg: String,
    @SerializedName("description_ru")
    val descriptionRu: String,
    @SerializedName("description_kg")
    val descriptionKg: String,
    @SerializedName("content_ru")
    val contentRu: String,
    @SerializedName("content_kg")
    val contentKg: String,
    val image: String,
) {
    fun convertDTO(isRusLang: Boolean) : CategoryFullResponseDTO{
        return CategoryFullResponseDTO(subcategories = this.subcategories ,
        slug = this.slug ,
        title = if(isRusLang) titleRu else titleKg ,
        description = if(isRusLang) descriptionRu else descriptionKg ,
        content = if(isRusLang) contentRu else contentKg ,
        image = this.image)
    }

}

data class SubCarResp(
    val duration: Int,
    val slug: String,
    @SerializedName("title_ru")
    val titleRu: String,
    @SerializedName("title_kg")
    val titleKg: String
) {
    fun convertDTO(isRusLang: Boolean): SubCarRespDTO {
        return SubCarRespDTO(
            duration = this.duration,
            slug = this.slug,
            title = if(isRusLang) this.titleRu else this.titleKg
            )
    }
}


data class SubCarRespDTO(
    val duration: Int,
    val slug: String,
    val title: String
)

data class CategoryFullResponseDTO(
    val subcategories: ArrayList<SubCarResp>,
    val slug: String,
    val title: String,
    val description: String,
    val content: String,
    val image: String,
)
