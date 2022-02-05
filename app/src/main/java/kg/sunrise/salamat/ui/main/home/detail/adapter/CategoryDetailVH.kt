package kg.sunrise.salamat.ui.main.home.detail.adapter

import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import kg.sunrise.salamat.R
import kg.sunrise.salamat.base.adapter.BaseVH
import kg.sunrise.salamat.data.remote.response.SubCarResp

class CategoryDetailVH(parent: ViewGroup, @LayoutRes id: Int) : BaseVH<SubCarResp>(parent, id) {
    private val title = itemView.findViewById<TextView>(R.id.tv_title)
    override fun bind(item: SubCarResp) {
        item.convertDTO(true)
        title.text = item.titleRu
    }
}