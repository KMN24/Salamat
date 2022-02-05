package kg.sunrise.salamat.ui.main.home.freqAskedQues.adapter

import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.adapter.BaseVH
import kg.sunrise.salamat.data.remote.response.FreqAskedQuesResponse

class FAQVH(parent: ViewGroup, @LayoutRes id: Int) : BaseVH<FreqAskedQuesResponse>(parent, id)  {
    private val quesTitle = itemView.findViewById<TextView>(R.id.question)
    override fun bind(item: FreqAskedQuesResponse) {
        quesTitle.text = item.issue
    }
}