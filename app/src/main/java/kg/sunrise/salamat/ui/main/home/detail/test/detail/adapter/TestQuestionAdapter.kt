package kg.sunrise.salamat.ui.main.home.detail.test.detail.adapter

import android.view.View
import android.view.ViewGroup
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.adapter.BaseAdapter
import kg.sunrise.salamat.data.remote.response.Answer
import kg.sunrise.salamat.utils.extesions.tryExtension
import timber.log.Timber

class TestQuestionAdapter(private var itemClick: (Answer) -> Unit) :
    BaseAdapter<Answer, QuestionViewHolder>(
    ) {
    var isCLickedOnItem = false
    var view: View? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val holder = QuestionViewHolder(parent, R.layout.vh_question_item)
        holder.itemView.setOnClickListener {
            view = it
            if (isCLickedOnItem)
                itemClick(items[holder.adapterPosition])
        }
        return holder
    }
}
