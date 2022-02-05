package kg.sunrise.salamat.ui.main.home.detail.test.detail.adapter

import android.view.ViewGroup
import android.widget.TextView
import android.widget.TimePicker
import androidx.annotation.LayoutRes
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.adapter.BaseVH
import kg.sunrise.salamat.data.remote.response.Answer
import kg.sunrise.salamat.data.remote.response.TestQuestionsItem
import timber.log.Timber

class QuestionViewHolder(parent: ViewGroup, @LayoutRes id: Int) :
    BaseVH<Answer>(parent, id) {
    private val textAnswer = itemView.findViewById<TextView>(R.id.tv_question_answer)
    override fun bind(item: Answer) {
        textAnswer.text = item.answer_ru
    }
}