package kg.sunrise.salamat.data.remote.response

import com.google.gson.annotations.SerializedName

data class TestQuestionsItem(
    val answers: ArrayList<Answer>,
    val category: String,
    val id: Int,
    @SerializedName("question_kg")
    val questionKg: String,
    @SerializedName("question_ru")
    val questionRu: String
)

data class Answer(
    val answer_kg: String,
    val answer_ru: String,
    @SerializedName("is_correct")
    val isCorrect: Boolean
)
