package kg.sunrise.salamat.ui.main.home.freqAskedQues.adapter

import android.view.ViewGroup
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.adapter.BaseAdapter
import kg.sunrise.salamat.data.remote.response.FreqAskedQuesResponse

class FAQAdapter : BaseAdapter<FreqAskedQuesResponse, FAQVH>() {
    var itemClick : (FreqAskedQuesResponse) -> Unit = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FAQVH {
        val holder = FAQVH(parent, R.layout.rv_item_freq_asked_ques)
        holder.itemView.setOnClickListener { itemClick(items[holder.adapterPosition]) }
        return holder
    }
}